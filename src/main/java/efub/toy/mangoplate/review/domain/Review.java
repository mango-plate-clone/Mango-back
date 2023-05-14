package efub.toy.mangoplate.review.domain;

import com.sun.istack.NotNull;
import efub.toy.mangoplate.global.BaseEntity;
import efub.toy.mangoplate.member.domain.Member;
import efub.toy.mangoplate.review.dto.request.ReviewUpdateReqDto;
import efub.toy.mangoplate.store.domain.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long reviewId;

    @Max(value = 5, message = "별점은 최대 5점입니다.")
    @Min(value = 1, message = "별점은 최소 1점입니다." )
    private int star;

    @NotNull
    @Column(name="title", length =100)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="has_image")
    private Boolean hasImage;

    @Column(name="image_url", nullable=true)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @Builder
    public Review(Member member, Store store, int star, String title, String content, Boolean hasImage, String imageUrl) {
        this.star = star;
        this.title = title;
        this.content = content;
        this.hasImage = hasImage;
        this.imageUrl = imageUrl;
        this.store = store;
        this.member= member;
    }

    public void update(ReviewUpdateReqDto reqDto){
        this.content = reqDto.getContent();
        this.star = reqDto.getStar();
        this.title = reqDto.getTitle();
    }

}
