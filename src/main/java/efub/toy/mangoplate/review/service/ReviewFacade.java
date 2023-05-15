package efub.toy.mangoplate.review.service;

import efub.toy.mangoplate.global.CustomException;
import efub.toy.mangoplate.global.ErrorCode;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.review.domain.Review;
import efub.toy.mangoplate.review.dto.SortType;
import efub.toy.mangoplate.review.dto.request.ReviewReqDto;
import efub.toy.mangoplate.review.dto.request.ReviewUpdateReqDto;
import efub.toy.mangoplate.review.dto.response.ReviewResDto;
import efub.toy.mangoplate.store.domain.Store;
import efub.toy.mangoplate.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ReviewFacade {
    private final ReviewService reviewService;
    private final StoreService storeService;

    @Transactional
    public ReviewResDto createByReviewReqDto(Member member, ReviewReqDto reviewReqDto){
        Store store  = storeService.findStoreById(reviewReqDto.getStoreId());
        int reviewCount = reviewService.getReviewCount(store.getStoreId());
        store.updateStar(reviewReqDto.getStar(), reviewCount);
        Review review = reviewService.createByReviewReqDto(member,store, reviewReqDto);
        return ReviewResDto.builder()
                .review(review)
                .build();
    }

    @Transactional(readOnly = true)
    public List<ReviewResDto> getReviewList(Long storeId, SortType sortType) {
        List<ReviewResDto> reviewResDtoList = reviewService.getReviewList(storeId, sortType);
        return reviewResDtoList;
    }

    @Transactional
    public ReviewResDto updateReview(Member member, Long reviewId, ReviewUpdateReqDto reviewUpdateReqDto) {
        Review review = reviewService.getReviewById(reviewId);
        if(!review.getMember().getMemberId().equals(member.getMemberId())){
            throw new CustomException(ErrorCode.INVALID_MEMBER);
        }
       ReviewResDto reviewResDto = reviewService.updateReview(reviewId, reviewUpdateReqDto);
       return reviewResDto;
    }

    @Transactional
    public void removeReview(Member member, Long reviewId){
        Review review = reviewService.getReviewById(reviewId);
        if(!review.getMember().getMemberId().equals(member.getMemberId())){
            throw new CustomException(ErrorCode.INVALID_MEMBER);
        }
        reviewService.removeReview(review);
    }
}
