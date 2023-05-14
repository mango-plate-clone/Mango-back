package efub.toy.mangoplate.hearts.dto;

import efub.toy.mangoplate.hearts.domain.Heart;
import lombok.Getter;

@Getter
public class HeartStoreDto {
    private Long heartId;
    private Long memberId;
    private Long storeId;
    private String name;
    private String imageUrl;

    public HeartStoreDto(Heart heart) {
        this.heartId = heart.getHeartId();
        this.memberId = heart.getMember().getMemberId();
        this.storeId = heart.getStore().getId();
        this.name = heart.getStore().getName();
        this.imageUrl = heart.getStore().getImageUrl();
    }
}
