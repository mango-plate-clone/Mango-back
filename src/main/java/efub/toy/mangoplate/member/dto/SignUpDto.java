package efub.toy.mangoplate.member.dto;

import efub.toy.mangoplate.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpDto {
    public String email;
    public String nickname;

    @Builder
    public SignUpDto(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
