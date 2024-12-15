package umc7.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QPageRequest;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {

    //Store를 사용해 미션들을 페이지형태로 리턴
    //자동으로 쿼리문 생성
    Page<Mission> findAllByStore(Store store, PageRequest PageRequest);
}
