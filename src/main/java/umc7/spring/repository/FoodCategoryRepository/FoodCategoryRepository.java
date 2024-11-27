package umc7.spring.repository.FoodCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

}
