package umc7.spring.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Review;
import umc7.spring.domain.enums.MissionStatus;
import umc7.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long>, MemberMissionRepositoryCustom{
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, PageRequest pageRequest);
    MemberMission findByMissionIdAndMemberId(Long missionId,Long memberId);
}
