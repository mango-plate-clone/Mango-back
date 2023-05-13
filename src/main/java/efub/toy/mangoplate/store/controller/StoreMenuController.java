package efub.toy.mangoplate.store.controller;

import efub.toy.mangoplate.menu.domain.Menu;
import efub.toy.mangoplate.menu.service.MenuService;
import efub.toy.mangoplate.store.dto.StoreMenusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreMenuController {
    private final MenuService menuService;

    // 가게&음식 카테고리별 메뉴 조회
    @GetMapping("/stores/{storeId}/menus/{category}")
    @ResponseStatus(HttpStatus.OK)
    public StoreMenusResponseDto readStoreMenus(@PathVariable Long storeId, @PathVariable String category){
        List<Menu> menuList = menuService.findCategoryMenuList(category, storeId);
        return StoreMenusResponseDto.of(storeId, menuList);
    }
}
