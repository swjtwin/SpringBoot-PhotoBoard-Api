package shop.shopping.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 중복 아이디
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "");

    private HttpStatus httpStauts;
    private String message;
}
