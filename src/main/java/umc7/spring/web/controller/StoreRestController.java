package umc7.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.service.ReviewService.ReviewCommandService;
import umc7.spring.validation.annotation.ExistStore;
import umc7.spring.web.dto.ReviewDto.ReviewRequestDto;
import umc7.spring.web.dto.ReviewDto.ReviewResponseDto;

@Tag(name="Store", description = "상점리뷰 관련 API")
@Validated
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor   //클래스의 final로 선언된 필드에 대해 생성자를 자동으로 생성. 의존성 주입을 간편하게 처리하기 위해 사용.
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;
    @Operation(
            summary = "리뷰 작성하기",
            description = "특정 가게에 리뷰를 추가합니다."
    )
    @Parameter(name="storeId", description = "가게 Id, path wariable입니다.", example = "1")
    @PostMapping("/{storeId}/review")
    public ApiResponse<ReviewResponseDto> createReview(
            @PathVariable("storeId") @ExistStore Long storeId,   //ExistStore >> 커스텀 어노테이션(우선 annotation파일만 만들어 두었음
            @RequestBody @Valid ReviewRequestDto request) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(new ReviewResponseDto(reviewCommandService.createReview(request, storeId, memberId)));
    }

}
