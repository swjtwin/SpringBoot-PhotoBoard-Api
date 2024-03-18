package shop.shopping.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 중복 아이디
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),

    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");


    private HttpStatus httpStauts;
    private String message;
}
