package umc7.spring.service.MissionService;

import umc7.spring.domain.Mission;
import umc7.spring.domain.mapping.MemberMission;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;

public interface MissionCommandService {
    public Mission createMission(MissionRequestDto request, Long storeId, Long memberId);
    public MemberMission addChallenge(Long missionId, Long memberId);
    public void completeMission(Long missionId, Long memberId);
}
