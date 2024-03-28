package shop.shopping.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import shop.shopping.entity.UploadImage;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 생성
@Getter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 생성
public class BoardDto {

    private Long id;
    private String username;
    private String nickname;
    private String title;
    private String content;
    private Integer likeCnt;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private MultipartFile newImage;
    private UploadImage uploadImage;

}
