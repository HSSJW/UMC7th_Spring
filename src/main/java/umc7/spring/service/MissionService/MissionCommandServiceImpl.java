package umc7.spring.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.converter.MissionConverter;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.repository.MissionRepository.MissionRepository;
import umc7.spring.repository.StoreRepository.StoreRepository;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;


@Service
@Transactional(readOnly = true) //기본적으로 이 클래스는 읽기 전용으로 실행되지만
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository; //Mission의 데이터베이스에 접근하기위함
    private final StoreRepository storeRepository; //storeId를 바탕으로

    @Override
    @Transactional //이 매서드는 쓰기/읽기 모두 가능하다
    public Mission createMission(MissionRequestDto request, Long storeId, Long memberId){
        Store store = storeRepository.findById(storeId).get(); //해당 Store 검색
        Mission newMission = MissionConverter.toMission(request, store); //컨버터에서 새로운 미션 생성
        store.getMissionList().add(newMission);
        return  missionRepository.save(newMission); //save 함수는 INSERT or UPDATE기능으로 retrun값으로 해당 엔티티를 반환한다.
    }


}
