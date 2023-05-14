package efub.toy.mangoplate.hearts.controller;


import efub.toy.mangoplate.config.authentication.AuthUser;
import efub.toy.mangoplate.hearts.dto.HeartStoreDto;
import efub.toy.mangoplate.hearts.service.HeartService;
import efub.toy.mangoplate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hearts")
public class HeartController {
    private  final HeartService heartService;

    @PostMapping("/{id}")
    public ResponseEntity<?> likeStore(@PathVariable Long id, @AuthUser Member member) {
        String response = heartService.likeStore(id, member);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelStore(@PathVariable Long id, @AuthUser Member member) {
        String response = heartService.cancelStoreLike(id, member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public List<HeartStoreDto> getHeartStoreList(@AuthUser Member member) {
        return heartService.findHeartStoreList(member);
    }

}
