package umc7.spring.converter;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Mission;
import umc7.spring.domain.mapping.MemberMission;
import umc7.spring.web.dto.MissionDto.MissionsResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


//
public class MissionsConverter {

    // 미션 1개를 DTO로 변환
    public static MissionsResponseDTO.MissionPreViewDTO missionsPreviewDTO(Mission mission) {
        return MissionsResponseDTO.MissionPreViewDTO.builder()
                .id(mission.getId())
                .name("Mission " + mission.getId()) // 예시로 이름 생성
                .content(mission.getContent())
                .reward(mission.getReward())
                .status(mission.getMemberMissionList().isEmpty()
                        ? null
                        : mission.getMemberMissionList().get(0).getStatus()) // 상태 예시
                .build();
    }

    // 미션 리스트를 Page 형태로 변환
    public static MissionsResponseDTO.MissionPreViewListDTO missionsPreViewListDTO(Page<Mission> missionList) {
        return MissionsResponseDTO.MissionPreViewListDTO.builder()
                .missionList(
                        missionList.stream()
                                .map(MissionsConverter::missionsPreviewDTO) // 각 미션을 DTO로 변환
                                .collect(Collectors.toList())
                )
                .listSize(missionList.getContent().size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

    // 단일 MemberMission -> MissionPreViewDTO 변환
    public static MissionsResponseDTO.MissionPreViewDTO memberMissionToMissionPreViewDTO(MemberMission memberMission) {
        return MissionsResponseDTO.MissionPreViewDTO.builder()
                .id(memberMission.getMission().getId()) // 미션 ID
                .name(memberMission.getMission().getStore().getName()) // 가게 이름 (null 처리)
                .content(memberMission.getMission().getContent())
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus()) // 현재 미션 상태
                .build();
    }


    // Page<MemberMission> -> MissionPreViewListDTO 변환
    public static MissionsResponseDTO.MissionPreViewListDTO memberMissionToMissionPreViewListDTO(Page<MemberMission> memberMissionPage) {
        // MemberMission 데이터를 MissionPreViewDTO로 변환
        List<MissionsResponseDTO.MissionPreViewDTO> missionPreViewDTOList = memberMissionPage.getContent().stream()
                .map(MissionsConverter::memberMissionToMissionPreViewDTO) // 각 MemberMission -> DTO 변환
                .collect(Collectors.toList());

        // MissionPreViewListDTO 생성
        return MissionsResponseDTO.MissionPreViewListDTO.builder()
                .missionList(missionPreViewDTOList)
                .listSize(missionPreViewDTOList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
