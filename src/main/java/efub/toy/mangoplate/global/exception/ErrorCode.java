package efub.toy.mangoplate.global.exception;

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
    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 리뷰입니다."),

    HAS_IMAGE_ERROR(HttpStatus.BAD_REQUEST, "이미지를 업로드 하려면 hasImage 값을 true로 설정해야 합니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."),
    CONVERT_MULTIPARTFILE_ERROR(HttpStatus.BAD_REQUEST, "MultipartFile을 File로 전환하는 것에 실패했습니다."),

    NON_LOGIN(HttpStatus.UNAUTHORIZED, "로그인 후 이용 가능합니다.");

    private final HttpStatus status;
    private final String message;
}
