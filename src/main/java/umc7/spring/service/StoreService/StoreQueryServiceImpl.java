package umc7.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.repository.ReviewRepository.ReviewRepository;
import umc7.spring.repository.StoreRepository.StoreRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Override
    public boolean storeExist(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page){

        Store store = storeRepository.findById(StoreId).get();

        //findAllByStore >> store객체를 통해 해당 스토어의 모든 리뷰를 검색한다
        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Review> getMyReviewList(Long StoreId, Long memberId, Integer page) {

        Store store = storeRepository.findById(StoreId).get(); //가게 특정

        Page<Review> myReviewPage = reviewRepository.findAllByStoreAndMemberId(store, memberId, PageRequest.of(page, 10));


        return myReviewPage;
    }


}