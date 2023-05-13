package efub.toy.mangoplate.menu.domain;

import efub.toy.mangoplate.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String category;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 30)
    private Long price;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Menu(String category, String name, Long price, String imageUrl, Store store){
        this.category = category;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.store = store;
    }
}
