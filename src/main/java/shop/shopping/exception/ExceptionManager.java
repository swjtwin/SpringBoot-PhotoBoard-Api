package shop.shopping.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
    에러 코드 정의하기
 */
@RestControllerAdvice // 예외 처리 어노테이션
public class ExceptionManager {

    // 요청이 들어왔을 때 에러가 나면 ExceptionHandler 들어와 에러 처리
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appExceptionHandler(AppException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStauts())
                .body(e.getErrorCode().name() + "  " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }


}
