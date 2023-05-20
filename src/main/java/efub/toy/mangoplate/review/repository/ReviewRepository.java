package efub.toy.mangoplate.review.repository;

import efub.toy.mangoplate.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    int countByStoreStoreId(Long storeId);
    List<Review> findAllByStoreStoreIdOrderByCreatedAtDesc(Long storeId);
    List<Review> findAllByStoreStoreIdOrderByStarDesc(Long storeId);

}
