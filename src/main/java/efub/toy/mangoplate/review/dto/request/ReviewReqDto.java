package efub.toy.mangoplate.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReqDto {
    private String title;
    private String content;
    private int star;
    private Boolean hasImage;
    private Long memberId;
    private Long storeId;

}
