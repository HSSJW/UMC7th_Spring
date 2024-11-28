package umc7.spring.web.dto.ReviewDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor  //모든 필드를 포함하는 생성자 자동 생성
@NoArgsConstructor   // 파라미터가 없는 기본 생성자를 자동 생성
@Builder
public class ReviewRequestDto { //리뷰작성요청할때 전달받은 데이터를 담음

    @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다. 최대 500자 까지 입력 가능") //null, 빈 문자열, 공백 문자열을 허용하지 않음
    @Size(max = 500) //길이 제한
    @Schema(description = "리뷰 내용", example = "치킨 피자 케익 버거 날 유혹해", type="String") //swagger
    private String content; //저장(다룰) 데이터 타입

    
    //message >> 유효성검사가 실패했을 때 클라이언트에게 제공할 오류 메시지
    @NotNull(message = "별점은 필수 입력 항목입니다.")
    @DecimalMin(value = "0.0", message = "별점은 최소 0.0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "별점은 최대 5.0 이하이어야 합니다.")
    @Schema(description = "별점", example = "2.1", type = "number")   //swagger
    private Float score;

}
