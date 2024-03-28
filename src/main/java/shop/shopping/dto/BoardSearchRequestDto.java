package shop.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class BoardSearchRequestDto {

    private String sortType;
    private String searchType;
    private String keyword;
}
