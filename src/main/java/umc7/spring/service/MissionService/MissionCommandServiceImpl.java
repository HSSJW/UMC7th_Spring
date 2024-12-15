package umc7.spring.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.converter.MemberMissionConverter;
import umc7.spring.converter.MissionConverter;
import umc7.spring.domain.Member;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.domain.enums.MissionStatus;
import umc7.spring.domain.mapping.MemberMission;
import umc7.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc7.spring.repository.MemberRepository.MemberRepository;
import umc7.spring.repository.MissionRepository.MissionRepository;
import umc7.spring.repository.StoreRepository.StoreRepository;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;

import java.util.Optional;


@Service
@Transactional(readOnly = true) //기본적으로 이 클래스는 읽기 전용으로 실행되지만
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository; //Mission의 데이터베이스에 접근하기위함
    private final StoreRepository storeRepository; //storeId를 바탕으로
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional //이 매서드는 쓰기/읽기 모두 가능하다
    public Mission createMission(MissionRequestDto request, Long storeId, Long memberId){
        Store store = storeRepository.findById(storeId).get(); //해당 Store 검색
        Mission newMission = MissionConverter.toMission(request, store); //컨버터에서 새로운 미션 생성
        store.getMissionList().add(newMission);
        return  missionRepository.save(newMission); //save 함수는 INSERT or UPDATE기능으로 retrun값으로 해당 엔티티를 반환한다.
    }


    @Override
    @Transactional
    public MemberMission addChallenge(Long missionId, Long memberId){
        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(memberId).get();
        MemberMission memberMission = MemberMissionConverter.toMissionChallenge(member, mission);


        return  memberMissionRepository.save(memberMission);
    }


    //미션완료시키는기능
    @Override
    @Transactional
    public void completeMission(Long missionId, Long memberId) {
        //MemberMission 조회
        MemberMission memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, memberId);

        //CHALLENGING인지 확인
        if (memberMission.getStatus() != MissionStatus.CHALLENGIN) {
            return;
        }

        //COMPLETE로 변경
        memberMission.setStatus(MissionStatus.COMPLETE);


        //포인트 증가
        Member member = memberMission.getMember();
        member.setPoint(member.getPoint() + memberMission.getMission().getReward());// reward 추가
        memberRepository.save(member); // MemberRepository를 사용하여 저장

        //MemberMission 저장
        memberMissionRepository.save(memberMission);
    }
}
