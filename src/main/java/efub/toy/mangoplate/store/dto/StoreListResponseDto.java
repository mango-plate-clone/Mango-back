package efub.toy.mangoplate.store.dto;

import efub.toy.mangoplate.store.domain.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class StoreListResponseDto {
    private List<StoreResponseDto> stores;

    public static StoreListResponseDto of(List<Store> storeList){
        return StoreListResponseDto.builder()
                .stores(storeList.stream().map(StoreResponseDto::from).collect(Collectors.toList()))
                .build();
    }
}
