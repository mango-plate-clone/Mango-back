package efub.toy.mangoplate.member.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @NotNull
    @Column(length = 30)
    private String email;

    @NotNull
    @Column(length = 50)
    private String nickname;


    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

}
