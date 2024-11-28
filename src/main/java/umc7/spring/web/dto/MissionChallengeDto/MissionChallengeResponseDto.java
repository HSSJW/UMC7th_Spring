package umc7.spring.web.dto.MissionChallengeDto;

import umc7.spring.domain.enums.MissionStatus;
import umc7.spring.domain.mapping.MemberMission;

public class MissionChallengeResponseDto {
    private Long id;
    private Long memberId;
    private  Long missionId;
    private Boolean status;
    private String missionContent;
    private Integer missionReward;

    public MissionChallengeResponseDto(MemberMission memberMission) {
        this.id = memberMission.getId();
        this.memberId = memberMission.getMember().getId();
        this.missionId = memberMission.getMission().getId();
        this.missionContent = memberMission.getMission().getContent();
        this.missionReward = memberMission.getMission().getReward();
        // MissionStatus를 Boolean 값으로 변환
        this.status = memberMission.getStatus() == MissionStatus.CHALLENGING;
    }

}
