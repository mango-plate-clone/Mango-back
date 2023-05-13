package efub.toy.mangoplate.store.dto;

import efub.toy.mangoplate.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Integer isParking;
    private String operationHours;
    private Long averagePrice;
    private Float starAverage;
    private String starCount;

    public StoreResponseDto(Long id, String name, String address, String phone, Integer isParking,
                            String operationHours, Long averagePrice, Float starAverage, String starCount){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isParking = isParking;
        this.operationHours = operationHours;
        this.averagePrice = averagePrice;
        this.starAverage = starAverage;
        this.starCount = starCount;
    }

    public static StoreResponseDto from(Store store){
        return new StoreResponseDto(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getPhone(),
                store.getIsParking(),
                store.getOperationHours(),
                store.getAveragePrice(),
                store.getStarAverage(),
                store.getStarCount());
    }
}
