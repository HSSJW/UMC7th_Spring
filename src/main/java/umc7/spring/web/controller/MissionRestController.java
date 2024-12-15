package umc7.spring.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.converter.MissionsConverter;
import umc7.spring.domain.Mission;
import umc7.spring.domain.mapping.MemberMission;
import umc7.spring.service.MissionService.MissionCommandService;
import umc7.spring.service.MissionService.MissionQueryService;
import umc7.spring.validation.annotation.CheckMission;
import umc7.spring.validation.annotation.CheckPage;
import umc7.spring.validation.annotation.ExistStore;
import umc7.spring.web.dto.MissionChallengeDto.MissionChallengeResponseDto;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;
import umc7.spring.web.dto.MissionDto.MissionResponseDto;
import umc7.spring.web.dto.MissionDto.MissionsResponseDTO;

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



    //---------------------------------------------------------------------------- /missions/
    private final MissionQueryService missionQueryService; //인터페이스형태로 선언

    @Operation(
            summary = "가게의 미션목록을 리턴하는 api",
            description = "userId에 맞게 우측상탄 미션의 상태다르게 출력"
    )
    @GetMapping("/{storeId}") // MissionPreViewListDTO의 형태로 리턴
    @Parameter(name="storeId", description = "미션목록의 조회할 가게 Id, path variable", example = "1", in = ParameterIn.PATH)
    @Parameter(name = "page", description = "페이지 번호, 1부터 시작", example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "userId", description = "사용자 ID", example = "1", in = ParameterIn.QUERY)

    public ApiResponse<MissionsResponseDTO.MissionPreViewListDTO> getMissionList(
            @PathVariable(name = "storeId") Long storeId, //path 파라미터
            @CheckPage @RequestParam(name = "page") Integer page, //유효성 검사
            @RequestParam(name = "userId") Long userId
    ){
        System.out.println("=== Controller.getMissionList ===");

        System.out.println("Received page value: " + page);
        Page<Mission> missionsList = missionQueryService.getMissionsList(storeId, userId, page-1);
        System.out.println("Final page value used in query: " + page);

        System.out.println("변경된 페이지값은" + page);
        return ApiResponse.onSuccess(MissionsConverter.missionsPreViewListDTO(missionsList));
    }


    //--------------------------------------------------------------------------------------- 특정가게 미션목록

    @Operation(
            summary = "현재 사용자가 진행중인(CHALLENGING)인 미션목록",
            description = "userId에 맞게 리턴"
    )
    @Parameter(name = "page", description = "페이지 번호, 1부터 시작", example = "1", in = ParameterIn.QUERY)
    @Parameter(name = "userId", description = "사용자 ID", example = "1", in = ParameterIn.QUERY)
    @GetMapping("/challenging")
    public ApiResponse<MissionsResponseDTO.MissionPreViewListDTO> getChallengingMissions(
            @RequestParam(name = "userId") Long userId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {

        // Service 호출
        Page<MemberMission> challengingMissions = missionQueryService.getChallengingMissions(userId, page-1);

        return ApiResponse.onSuccess(MissionsConverter.memberMissionToMissionPreViewListDTO(challengingMissions));
    }

    //------------------------------------------------------------------------------------------ 내가 진행중인 미션

    @Operation(
            summary = "진행 중인 미션을 완료로 바꾸고 사용자의 포인트를 증가시키는 api",
            description = "해당 미션의 reward만큼 포인트 증가"
    )
    @Parameter(name = "missionId", description = "미션id", example = "1", in = ParameterIn.PATH)
    @Parameter(name = "userId", description = "사용자id", example = "1", in = ParameterIn.QUERY)
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<String> completeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestParam(name = "userId") Long userId
    ) {

        missionCommandService.completeMission(missionId, userId);
        return ApiResponse.onSuccess("MissionStatus set SUCCESS");
    }

}
