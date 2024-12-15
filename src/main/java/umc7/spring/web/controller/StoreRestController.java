package umc7.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.converter.StoreConverter;
import umc7.spring.domain.Review;
import umc7.spring.service.ReviewService.ReviewCommandService;
import umc7.spring.service.StoreService.StoreQueryService;
import umc7.spring.validation.annotation.ExistStore;
import umc7.spring.web.dto.ReviewDto.ReviewRequestDto;
import umc7.spring.web.dto.ReviewDto.ReviewResponseDto;
import umc7.spring.web.dto.StoreDTO.StoreResponseDTO;

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

    private final StoreQueryService storeQueryService;



    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })

    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!", example = "0")
    })

    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> //조회결과를 이DTO에서 나온 형태로 맵핑
    getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){ //ExistStore : storeId 유효성 검증
        System.out.println("-----------" + storeId + "페이지:" + page);
        Page<Review>  reviewList = storeQueryService.getReviewList(storeId, page); //storeId와 page수를 전달하고 리뷰 리스트 받아옴
        
        //Converter에 맞게 만들어줌
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

}
