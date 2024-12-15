package umc7.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Mission;
import umc7.spring.domain.mapping.MemberMission;

public interface MissionQueryService {
    public boolean isChallengedMission(Long memberId, Long missionId);

    public Page<Mission> getMissionsList(Long storeId, Long userId,Integer page);

    Page<MemberMission> getChallengingMissions(Long userId, Integer page);
}
