package efub.toy.mangoplate.review.controller;

import efub.toy.mangoplate.config.authentication.AuthUser;
import efub.toy.mangoplate.global.BaseEntity;
import efub.toy.mangoplate.global.exception.CustomException;
import efub.toy.mangoplate.global.exception.ErrorCode;
import efub.toy.mangoplate.global.service.S3Uploader;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController extends BaseEntity {
    private final ReviewFacade reviewFacade;
    private final S3Uploader s3Uploader;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResDto> createReview(@AuthUser Member member, @RequestPart(value = "image",required = false) MultipartFile image, @RequestPart(value = "dto") ReviewReqDto requestDto) throws IOException {
        if (requestDto.getHasImage().equals(false) && !image.isEmpty()){
            throw new CustomException(ErrorCode.HAS_IMAGE_ERROR);
        }
        String fileUrl = null;
        if(requestDto.getHasImage().equals(true) && !image.isEmpty()){
             fileUrl = s3Uploader.upload(image, "image");
        }
        ReviewResDto reviewResDto =reviewFacade.createByReviewReqDto(member, requestDto, fileUrl);
        return ResponseEntity.ok(reviewResDto);
    }

    @GetMapping("/list")
    public List<ReviewResDto> getReviewList(@RequestParam Long storeId, @RequestParam SortType sortType ){
        List<ReviewResDto> reviewResDtoList = reviewFacade.getReviewList(storeId, sortType);
        return reviewResDtoList;
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResDto> modifyReview(@AuthUser Member member,@PathVariable Long reviewId, @RequestBody ReviewUpdateReqDto reviewUpdateReqDto){
        ReviewResDto reviewResDto = reviewFacade.updateReview(member, reviewId, reviewUpdateReqDto);
        return ResponseEntity.ok(reviewResDto);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity removeReview(@AuthUser Member member, @PathVariable Long reviewId){
        reviewFacade.removeReview(member, reviewId);
        return ResponseEntity.ok().build();
    }
}
