package efub.toy.mangoplate.member.dto;

import efub.toy.mangoplate.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long memberId;
    private String email;
    private String nickname;
    private String accessToken;

    @Builder
    public LoginResponseDto(Member member, String accessToken){
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.accessToken = accessToken;
    }
}
