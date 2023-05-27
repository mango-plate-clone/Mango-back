package efub.toy.mangoplate.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.member.domain.oauth.KakaoProfile;
import efub.toy.mangoplate.member.domain.oauth.OAuthToken;
import efub.toy.mangoplate.member.dto.LoginResponseDto;
import efub.toy.mangoplate.member.dto.MemberResponseDto;
import efub.toy.mangoplate.member.dto.SignUpDto;
import efub.toy.mangoplate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    public Member getAccessToken(String code) throws UnsupportedEncodingException {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //발급받은 토큰 정보 oauthToken에 담기
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return findProfile(oauthToken);
    }

    private Member findProfile(OAuthToken oauthToken) {
        //2. 발급받은 토큰으로 사용자 정보 조회
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        //사용자 정보 kakaoProfile에 담기
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return saveMember(kakaoProfile);
    }


    public Member saveMember(@RequestBody KakaoProfile kakaoProfile) {
        //Member 저장
        Member kakaoMember = memberRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());

        if(kakaoMember == null) {
            //기존 회원이 아니면 자동 회원가입을 진행
            kakaoMember = Member.builder()
                    .email(kakaoProfile.getKakao_account().getEmail())
                    .nickname(kakaoProfile.getKakao_account().profile.nickname)
                    .build();
            memberRepository.save(kakaoMember);
        }
        return kakaoMember;
    }

    public LoginResponseDto login(Member member) {
        String authToken = jwtTokenProvider.createAccessToken(member.getMemberId());

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .member(member)
                .accessToken(authToken)
                .build();
        return loginResponseDto;
    }


    @Transactional
    public MemberResponseDto findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        return new MemberResponseDto(member);
    }

    public List<MemberResponseDto> findMemberList() {
        return memberRepository.findAll()
                .stream()
                .map(user -> new MemberResponseDto(user))
                .collect(Collectors.toList());
    }
}
