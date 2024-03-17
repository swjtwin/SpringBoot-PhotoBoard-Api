package shop.shopping.exception;

/*
*  예외처리 ErrorCode message 처리
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.shopping.constant.ErrorCode;

@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Getter
public class AppException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

}
