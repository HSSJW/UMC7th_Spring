package umc7.spring.converter;

import umc7.spring.domain.Member;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.web.dto.ReviewRequestDto;

public class ReviewConverter {

    public static Review toReview(Member requestMember, ReviewRequestDto request, Store store){
            return Review.builder()
                    .content(request.getContent())
                    .score(request.getScore())
                    .member(requestMember)
                    .store(store)
                    .build();
    }
}
