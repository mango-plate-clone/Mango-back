package efub.toy.mangoplate.menu.dto;

import efub.toy.mangoplate.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MenuResponseDto {
    private Long menuId;
    private String category;
    private String name;
    private Long price;
    private String imageUrl;
    private Long storeId;

    public static MenuResponseDto of(Menu menu){
        return MenuResponseDto.builder()
                .menuId(menu.getMenuId())
                .category(menu.getCategory())
                .name(menu.getName())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .storeId(menu.getStore().getStoreId())
                .build();
    }
}
