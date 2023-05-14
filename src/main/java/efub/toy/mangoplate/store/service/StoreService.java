package efub.toy.mangoplate.store.service;

import efub.toy.mangoplate.store.domain.Store;
import efub.toy.mangoplate.store.dto.StoreDto;
import efub.toy.mangoplate.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    public List<Store> findStoreList() {
        return storeRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Store findStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("해당 가게의 정보를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Store> findTypeStoreList(String type){
        return storeRepository.findAllByType(type);
    }

    public List<Store> findLocationStoreList(String location) {
        return storeRepository.findAllByLocation(location);
    }

    @Transactional
    public List<StoreDto> searchStore(String name) {
        List<Store> stores = storeRepository.findByNameContaining(name);
        List<StoreDto> storeDtoList = new ArrayList<>();
        
        if(stores.isEmpty()) return storeDtoList;
        
        for(Store store : stores){
            storeDtoList.add(this.convertEntityToDto(store));
        }
        return storeDtoList;
    }
    
    private StoreDto convertEntityToDto(Store store){
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .isParking(store.getIsParking())
                .operationHours(store.getOperationHours())
                .imageUrl(store.getImageUrl())
                .recommendation(store.getRecommendation())
                .averagePrice(store.getAveragePrice())
                .starAverage(store.getStarAverage())
                .starCount(store.getStarCount())
                .type(store.getType())
                .location(store.getLocation())
                .build();
    }
}
