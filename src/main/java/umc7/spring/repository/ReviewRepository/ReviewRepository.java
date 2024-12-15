package umc7.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
    //Spring Data JPA에서 메서드 이름마으로 SQL을 만드는 기능 활용
    Page<Review> findAllByStore(Store store, PageRequest pageRequest); //Store를 사용해 Review를 Page형태로 리턴하는 함수

    Page<Review> findAllByStoreAndMemberId(Store store, Long memberId, PageRequest pageRequest);
}
