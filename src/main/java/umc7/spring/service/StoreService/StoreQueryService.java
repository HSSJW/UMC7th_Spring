package umc7.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    public boolean storeExist(Long storeId);

    Page<Review> getReviewList(Long StoreId, Integer page); //특정 가게 리뷰 목록 조회

    Page<Review> getMyReviewList(Long StoreId, Long userId ,Integer page);
}