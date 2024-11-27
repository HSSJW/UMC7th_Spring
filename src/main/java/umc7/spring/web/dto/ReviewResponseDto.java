package umc7.spring.web.dto;

import lombok.*;
import umc7.spring.domain.Review;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long storeId;
    private String content;
    private Float score;


    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.storeId = review.getStore().getId();
        this.content = review.getContent();  //getContent함수 추가해줬음
        this.score = review.getScore();
    }
}
