package efub.toy.mangoplate.store.domain;

import efub.toy.mangoplate.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long storeId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(name = "is_parking", nullable = false)
    private Integer isParking;

    @Column(name = "operation_hours", nullable = false, length = 30)
    private String operationHours;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false, length = 100)
    private String recommendation;

    @Column(name = "average_price", nullable = false)
    private Long averagePrice;

    @Column(name = "star_average", nullable = false)
    private Float starAverage;

    @Column(name = "star_count", nullable = false, length = 30)
    private String starCount;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false, length = 30)
    private String location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>();

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
