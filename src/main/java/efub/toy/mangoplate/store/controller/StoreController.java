package efub.toy.mangoplate.store.controller;

import efub.toy.mangoplate.store.domain.Store;
import efub.toy.mangoplate.store.dto.StoreDto;
import efub.toy.mangoplate.store.dto.StoreResponseDto;
import efub.toy.mangoplate.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    //가게 상세 조회
    @GetMapping("/details/{storeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public StoreResponseDto getStore(@PathVariable Long storeId){
        Store store = storeService.findStoreById(storeId);
        return StoreResponseDto.from(store);
    }

    // 음식 타입별 가게 리스트 조회
    @GetMapping("/type/{type}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<StoreResponseDto> StoreTypeListFind(@PathVariable String type){
        List<Store> storeList = storeService.findTypeStoreList(type);
        return storeList.stream().map(StoreResponseDto::from).collect(Collectors.toList());
    }

    // 지역별 가게 리스트 조회
    @GetMapping("/location/{location}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<StoreResponseDto> StoreLocationListFind(@PathVariable String location){
        List<Store> storeList = storeService.findLocationStoreList(location);
        return storeList.stream().map(StoreResponseDto::from).collect(Collectors.toList());
    }

    // 가게 이름 검색
    @GetMapping("/search")
    public List<StoreDto> search(@RequestParam(value = "name") String name) {
        return storeService.searchStore(name);
    }
}
