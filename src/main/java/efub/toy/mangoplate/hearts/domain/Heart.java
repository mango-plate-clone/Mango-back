package efub.toy.mangoplate.hearts.domain;

import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.store.domain.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "heart")
@NoArgsConstructor
public class Heart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public Heart(Member member, Store store) {
        this.member = member;
        this.store = store;
    }
}
