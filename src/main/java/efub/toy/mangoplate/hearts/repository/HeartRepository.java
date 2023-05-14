package efub.toy.mangoplate.hearts.repository;

import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.store.domain.Store;
import efub.toy.mangoplate.hearts.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByMemberAndStore(Member member, Store store);
    List<Heart> findAllByMember(Member member);
}
