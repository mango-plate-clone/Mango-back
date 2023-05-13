package efub.toy.mangoplate.store.dto;

import efub.toy.mangoplate.store.domain.Store;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StoreDto {
    private Long id;
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

    public Store toEntity(){
        return Store.builder()
                .id(id)
                .name(name)
                .address(address)
                .phone(phone)
                .isParking(isParking)
                .operationHours(operationHours)
                .imageUrl(imageUrl)
                .recommendation(recommendation)
                .averagePrice(averagePrice)
                .starAverage(starAverage)
                .starCount(starCount)
                .type(type)
                .location(location)
                .build();
    }

    @Builder
    public StoreDto(Long id, String name, String address, String  phone, Integer isParking,
                    String operationHours, String imageUrl, String recommendation, Long averagePrice,
                    Float starAverage,String starCount, String type, String location){
        this.id = id;
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
}
