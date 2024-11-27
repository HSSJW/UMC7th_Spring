package umc7.spring.service.ReviewService;

import umc7.spring.domain.Review;
import umc7.spring.web.dto.ReviewRequestDto;

public interface ReviewCommandService {
    public Review createReview(ReviewRequestDto request, Long storeId, Long memberId);
}
