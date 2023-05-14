package efub.toy.mangoplate.review.dto.response;

import efub.toy.mangoplate.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResDto {
    private Long reviewId;
    private Long memberId;
    private Long storeId;
    private Integer star;
    private String title;
    private String content;
    private Boolean hasImage;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public ReviewResDto(Review review) {
        this.reviewId = review.getReviewId();
        this.memberId = review.getMember().getMemberId();
        this.storeId = review.getStore().getId();
        this.star = review.getStar();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.hasImage = review.getHasImage();
        this.imageUrl = review.getImageUrl();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
    }
}
