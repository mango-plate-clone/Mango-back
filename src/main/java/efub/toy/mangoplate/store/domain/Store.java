package efub.toy.mangoplate.store.domain;

import efub.toy.mangoplate.global.BaseEntity;
import efub.toy.mangoplate.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long storeId;

    @NotNull
    @Column(length = 30)
    private String name;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column(length = 30)
    private String phone;

    @NotNull
    @Column(name = "is_parking")
    private Integer isParking;

    @NotNull
    @Column(name = "operation_hours", length = 30)
    private String operationHours;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Column(length = 100)
    private String recommendation;

    @NotNull
    @Column(name = "average_price")
    private Long averagePrice;

    @NotNull
    @Column(name = "star_average")
    private Float starAverage;

    @NotNull
    @Column(name = "star_count", length = 30)
    private String starCount;

    @NotNull
    @Column(length = 30)
    private String type;

    @NotNull
    @Column(length = 30)
    private String location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>();

    public void updateStar(int star, int reviewCount){
        this.starAverage = Math.round((this.starAverage * reviewCount + star) / (reviewCount +1)*10)/10.0f; //소수점 첫째자리까지 반올림

        String[] ratings = this.starCount.split("\\|");
        ratings[star-1] = String.valueOf(Integer.parseInt(ratings[star-1]) + 1);
        String newStarCount = String.join("|", ratings);
        this.starCount = newStarCount;
    }

    @Builder
    public Store(Long storeId, String name, String address, String  phone, Integer isParking,
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
}
