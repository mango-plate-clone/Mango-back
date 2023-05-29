package efub.toy.mangoplate.member.service;

import efub.toy.mangoplate.global.exception.CustomException;
import efub.toy.mangoplate.global.exception.ErrorCode;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.member.repository.MemberRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtTokenProvider {
    private final MemberRepository userRepository;

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    private Long accessTokenValidTime = 1000L * 60 * 60 * 24 * 3;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createToken(String value, Long tokenValidTime){

        Claims claims = Jwts.claims().setSubject(value);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createAccessToken(Long userId){
        return this.createToken(userId.toString(), accessTokenValidTime);
    }

    public String resolveJwtToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Authentication getAuthenticationFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try {
            Long userId = Long.valueOf(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
            Member member = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
            return new UsernamePasswordAuthenticationToken(member, "");
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.NON_LOGIN);
        }
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
