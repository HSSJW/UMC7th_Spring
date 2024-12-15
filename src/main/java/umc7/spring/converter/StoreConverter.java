package umc7.spring.converter;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Review;
import umc7.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    //DTO객체를 통째로 만들어서 리턴
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){

        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName()) //매개변수로 전달받은 리뷰의 멤버를 연관관계를 통해 찾고 >> 멤버의 이름을 찾음
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())  //현재 페이지가 마지막인지
                .isFirst(reviewList.isFirst()) //현재 페이지가 첫페이지인지
                .totalPage(reviewList.getTotalPages()) //전체 페이지 수
                .totalElements(reviewList.getTotalElements()) //전체 데이터 수
                .listSize(reviewPreViewDTOList.size()) //
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
