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
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StoreResponseDto getStore(@PathVariable Long id){
        Store store = storeService.findStoreById(id);
        return StoreResponseDto.from(store);
    }

    // 음식 타입별 가게 리스트 조회
    @GetMapping("/{type}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<StoreResponseDto> StoreTypeListFind(@PathVariable String type){
        List<Store> storeList = storeService.findTypeStoreList(type);
        return storeList.stream().map(StoreResponseDto::from).collect(Collectors.toList());
    }

    // 지역별 가게 리스트 조회
    @GetMapping("/{location}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<StoreResponseDto> StoreLocationListFind(@PathVariable String location){
        List<Store> storeList = storeService.findLocationStoreList(location);
        return storeList.stream().map(StoreResponseDto::from).collect(Collectors.toList());
    }

    // 가게 이름 검색
    @GetMapping("/search") // 가게 이름 검색창 눌렀을 때
    public String search(@RequestParam(value = "name") String name, Model model) {
        List<StoreDto> storeDtoList = storeService.searchStore(name);
        model.addAttribute("storeSearchList", storeDtoList);
        return "/stores/search";
    }




}
