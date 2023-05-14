package efub.toy.mangoplate.store.repository;

import efub.toy.mangoplate.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByType(String type);
    List<Store> findByNameContaining(String sword);
    List<Store> findAllByLocation(String location);
}
