package efub.toy.mangoplate.review.controller;

import efub.toy.mangoplate.config.authentication.AuthUser;
import efub.toy.mangoplate.global.BaseEntity;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.review.dto.request.ReviewUpdateReqDto;
import efub.toy.mangoplate.review.dto.SortType;
import efub.toy.mangoplate.review.dto.request.ReviewReqDto;
import efub.toy.mangoplate.review.dto.response.ReviewResDto;
import efub.toy.mangoplate.review.service.ReviewFacade;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController extends BaseEntity {
    private final ReviewFacade reviewFacade;

    @PostMapping
    public ResponseEntity<ReviewResDto> createReview(@AuthUser Member member, @RequestBody @Valid ReviewReqDto requestDto){
        ReviewResDto reviewResDto = reviewFacade.createByReviewReqDto(member, requestDto);
        return ResponseEntity.ok(reviewResDto);
    }

    @GetMapping("/list")
    public List<ReviewResDto> getReviewList(@RequestParam Long storeId, @RequestParam SortType sortType ){
        List<ReviewResDto> reviewResDtoList = reviewFacade.getReviewList(storeId, sortType);
        return reviewResDtoList;
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResDto> modifyReview(@AuthUser Member member,@PathVariable Long reviewId, @RequestBody ReviewUpdateReqDto reviewUpdateReqDto){
        ReviewResDto reviewResDto = reviewFacade.updateReview(member, reviewUpdateReqDto);
        return ResponseEntity.ok(reviewResDto);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity removeReview(@AuthUser Member member, @PathVariable Long reviewId){
        reviewFacade.removeReview(member, reviewId);
        return ResponseEntity.ok().build();
    }
}
