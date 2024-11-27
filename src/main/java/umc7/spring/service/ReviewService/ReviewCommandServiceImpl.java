package umc7.spring.service.ReviewService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.apiPayload.exception.handler.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.MemberHandler;
import umc7.spring.converter.ReviewConverter;
import umc7.spring.domain.Member;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.repository.MemberRepository.MemberRepository;
import umc7.spring.repository.ReviewRepository.ReviewRepository;
import umc7.spring.repository.StoreRepository.StoreRepository;
import umc7.spring.web.dto.ReviewRequestDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    @Override
    @Transactional
    public Review createReview(ReviewRequestDto request, Long storeId, Long memberId) {
        Member requestMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId).get();
        Review newReview =  ReviewConverter.toReview(requestMember, request, store);
        store.getReviewList().add(newReview);
        requestMember.getReviewList().add(newReview);
        return reviewRepository.save(newReview);  //ReviewRepository에 아무것도 구현 안했는데 save는 무엇??
    }

}
