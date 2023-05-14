package efub.toy.mangoplate.review.repository;

import efub.toy.mangoplate.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    int countByStoreId(Long storeId);
    List<Review> findAllByStoreIdOrderByCreatedAtDesc(Long storeId);
    List<Review> findAllByStoreIdOrderByStarDesc(Long storeId);

}
