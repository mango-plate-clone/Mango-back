package efub.toy.mangoplate.store.dto;

import efub.toy.mangoplate.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class StoreMenusResponseDto {
    private Long storeId;
    private List<StoreMenu> menus;

    public static StoreMenusResponseDto of(Long storeId, List<Menu> menuList){
        return StoreMenusResponseDto.builder()
                .storeId(storeId)
                .menus(menuList.stream().map(StoreMenu::of).collect(Collectors.toList()))
                .build();
    }

    @Getter
    public static class StoreMenu{
        private Long menuId;
        private String category;
        private String name;
        private Long price;
        private String imageUrl;

        public StoreMenu(Menu menu){
            this.menuId = menu.getMenuId();
            this.category = menu.getCategory();
            this.name = menu.getName();
            this.price = menu.getPrice();
            this.imageUrl = menu.getImageUrl();
        }
        public static StoreMenu of(Menu menu){
            return new StoreMenu(menu);
        }
    }
}
