package efub.toy.mangoplate.menu.domain;

import com.sun.istack.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long MenuId;

    @NotNull
    @Column(length = 30)
    private String category;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(length = 30)
    private Long price;

    @NotNull
    @Column
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
