package umc7.spring.service.MissionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
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
            if (status != MissionStatus.CHALLENGIN) {
                return false;
            }
        }

        // 미션 상태가 없거나 완료 상태라면 true 반환
        return true;
    }

    //특정 가게 미션리스트
    @Override
    public Page<Mission> getMissionsList(Long storeId, Long userId, Integer page) {
        //Store 객체 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        //Store에 속한 미션들 조회 (페이징 적용)
        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

    //현재 사용자가 진행중인 미션들
    @Override
    public Page<MemberMission> getChallengingMissions(Long userId, Integer page) {

        return memberMissionRepository.findByMemberIdAndStatus(userId, MissionStatus.CHALLENGIN, PageRequest.of(page, 10));
    }
}
