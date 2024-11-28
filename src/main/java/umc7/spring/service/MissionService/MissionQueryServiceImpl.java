package umc7.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Member;
import umc7.spring.domain.enums.MissionStatus;
import umc7.spring.domain.mapping.MemberMission;
import umc7.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc7.spring.repository.MemberRepository.MemberRepository;
import umc7.spring.repository.MissionRepository.MissionRepository;
import umc7.spring.repository.StoreRepository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public boolean isChallengedMission(Long memberId, Long missionId) {
        Optional<MemberMission> missionStatus = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);
        // 미션 상태가 존재하고 완료 상태가 아니라면 false 반환
        if (missionStatus.isPresent()) {
            MissionStatus status = missionStatus.get().getStatus();
            if (status != MissionStatus.CHALLENGING) {
                return false;
            }
        }

        // 미션 상태가 없거나 완료 상태라면 true 반환
        return true;
    }
}
