package efub.toy.mangoplate.store.dto;

import efub.toy.mangoplate.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreResponseDto {
    private Long storeId;
    private String name;
    private String address;
    private String phone;
    private Integer isParking;
    private String operationHours;
    private String imageUrl;
    private String recommendation;
    private Long averagePrice;
    private Float starAverage;
    private String starCount;
    private String type;
    private String location;


    public StoreResponseDto(Long storeId, String name, String address, String phone, Integer isParking,
                            String operationHours, String imageUrl, String recommendation, Long averagePrice,
                            Float starAverage, String starCount, String type, String location){
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isParking = isParking;
        this.operationHours = operationHours;
        this.imageUrl = imageUrl;
        this.recommendation = recommendation;
        this.averagePrice = averagePrice;
        this.starAverage = starAverage;
        this.starCount = starCount;
        this.type = type;
        this.location = location;
    }

    public static StoreResponseDto from(Store store){
        return new StoreResponseDto(
                store.getStoreId(),
                store.getName(),
                store.getAddress(),
                store.getPhone(),
                store.getIsParking(),
                store.getOperationHours(),
                store.getImageUrl(),
                store.getRecommendation(),
                store.getAveragePrice(),
                store.getStarAverage(),
                store.getStarCount(),
                store.getType(),
                store.getLocation());
    }
}
