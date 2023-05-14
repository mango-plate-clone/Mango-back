package efub.toy.mangoplate.member.controller;

import efub.toy.mangoplate.config.authentication.AuthUser;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.member.dto.LoginResponseDto;
import efub.toy.mangoplate.member.dto.MemberResponseDto;
import efub.toy.mangoplate.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login/kakao")
    public LoginResponseDto kakaoLogin(@RequestParam("code") String code) {
        Member member = memberService.getAccessToken(code);
        return memberService.login(member);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@AuthUser Member member){
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping
    public MemberResponseDto getMemberById(@AuthUser Member member) {
        return memberService.findMemberById(member.getMemberId());
    }

    @GetMapping("/list")
    public List<MemberResponseDto> getMemberList() {

        return memberService.findMemberList();
    }

}
