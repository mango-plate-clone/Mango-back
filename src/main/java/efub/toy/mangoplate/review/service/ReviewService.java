package efub.toy.mangoplate.review.service;

import efub.toy.mangoplate.global.CustomException;
import efub.toy.mangoplate.global.ErrorCode;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.review.domain.Review;
import efub.toy.mangoplate.review.dto.SortType;
import efub.toy.mangoplate.review.dto.request.ReviewReqDto;
import efub.toy.mangoplate.review.dto.request.ReviewUpdateReqDto;
import efub.toy.mangoplate.review.dto.response.ReviewResDto;
import efub.toy.mangoplate.review.repository.ReviewRepository;
import efub.toy.mangoplate.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createByReviewReqDto(Member member, Store store, ReviewReqDto reviewReqDto) {
        Review review = Review.builder()
                .star(reviewReqDto.getStar())
                .title(reviewReqDto.getTitle())
                .content(reviewReqDto.getContent())
                .hasImage(reviewReqDto.getHasImage())
                .imageUrl(reviewReqDto.getImageUrl())
                .member(member)
                .store(store)
                .build();


        return reviewRepository.save(review);

    }

    @Transactional(readOnly = true)
    public int getReviewCount(Long storeId) {
        return reviewRepository.countByStoreId(storeId);
    }
    @Transactional(readOnly = true)
    public Review getReviewById(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(()->  new CustomException(ErrorCode.REVIEW_NOT_FOUND));
    }

    public List<ReviewResDto> getReviewList(Long storeId, SortType sortType) {
        List<Review> reviewList = new Stack<>();
        if(sortType.equals(SortType.LATEST)){
            reviewList = reviewRepository.findAllByStoreIdOrderByCreatedAtDesc(storeId);
        }

        if(sortType.equals(SortType.HIGHSCORE)){
            reviewList = reviewRepository.findAllByStoreIdOrderByStarDesc(storeId);
        }

        List<ReviewResDto> reviewResDtoList = reviewList.stream()
                .map(h -> ReviewResDto.builder()
                        .review(h)
                        .build())
                .collect(Collectors.toList());

        return reviewResDtoList;

    }

    public ReviewResDto updateReview(Long reviewId, ReviewUpdateReqDto reviewUpdateReqDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new CustomException(ErrorCode.REVIEW_NOT_FOUND));
        review.update(reviewUpdateReqDto);
        reviewRepository.save(review);

        return ReviewResDto.builder()
                .review(review)
                .build();

    }

    public void removeReview(Review review) {
        reviewRepository.delete(review);
    }
}
