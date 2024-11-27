package umc7.spring.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {

}
