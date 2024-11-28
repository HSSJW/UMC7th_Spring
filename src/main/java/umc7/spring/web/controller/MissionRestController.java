package umc7.spring.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.service.MissionService.MissionCommandService;
import umc7.spring.validation.annotation.CheckMission;
import umc7.spring.validation.annotation.ExistStore;
import umc7.spring.web.dto.MissionChallengeDto.MissionChallengeResponseDto;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;
import umc7.spring.web.dto.MissionDto.MissionResponseDto;

@Tag(name="Mission", description = "미션 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    //-------------------------------------------------------------------------
    @Operation(
            summary = "미션 만들기",
            description = "입력한 정보를 사용해 가게에 미션을 생성합니다."
    )
    @Parameter(name="storeId", description = "가게 Id, path variable 입니다.", example = "1")
    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponseDto> createMission(
            @RequestBody @Valid MissionRequestDto request,
            @PathVariable("storeId") @ExistStore Long storeId){  //@ExistStore 커스텀 어노테이션으로 해당 store가 존재하는 검증 >> 이 뒤로는 검사 안해도 됨
        Long memberId = 1L;
        return ApiResponse.onSuccess(new MissionResponseDto(missionCommandService.createMission(request, storeId, memberId)));
    }

    //-------------------------------------------------------------------------- /missions/{storeId}/mission


    @Operation(
            summary = "가게의 미션을 도전 중인 미션에 추가하는 api",
            description = "도전하려는 미션이 도전 중이 아니라면 가게의 미션을 도전 중인 미션에 추가"
    )
    @Parameter(name="missionId", description = "미션 Id, path variable입니다.", example = "1")
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<Object> addChallengeMission(
            @PathVariable("missionId") @CheckMission Long missionId
    ){
        Long memberId = 1L;
        return ApiResponse.onSuccess(new MissionChallengeResponseDto(missionCommandService.addChallenge(missionId, memberId)));

    }


}
