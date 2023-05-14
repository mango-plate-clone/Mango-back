package efub.toy.mangoplate.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateReqDto {
    private Long reviewId;
    private String title;
    private String content;
    private int star;
    private Boolean hasImage;
    private String imageUrl;
    private Long memberId;
    private Long storeId;
}
