package efub.toy.mangoplate.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //200
    SUCCESS(HttpStatus.OK, "OK"),

    //400
    INVALID_MEMBER(HttpStatus.BAD_REQUEST, "유효하지 않은 접근입니다."),
    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 리뷰입니다.");

    private final HttpStatus status;
    private final String message;
}
