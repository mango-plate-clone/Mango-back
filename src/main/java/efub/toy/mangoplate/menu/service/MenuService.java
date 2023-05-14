package efub.toy.mangoplate.menu.service;

import efub.toy.mangoplate.menu.domain.Menu;
import efub.toy.mangoplate.menu.repository.MenuRepository;
import efub.toy.mangoplate.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    private final StoreService storeService;

    public List<Menu> findCategoryMenuList(String category, Long storeId){
        return menuRepository.findByCategoryAndStore_storeId(category, storeId);
    }

}
