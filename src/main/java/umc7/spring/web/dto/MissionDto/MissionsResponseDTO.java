package umc7.spring.web.dto.MissionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc7.spring.domain.enums.MissionStatus;

import java.util.List;

public class MissionsResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        List<MissionPreViewDTO> missionList; //미션객체의 리스트
        Integer listSize;
        Integer totalPage;
        Long totalElements; //전체 요소 개수
        Boolean isFirst;
        Boolean isLast;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        private Long id;
        private String name;
        private String content;
        private Integer reward;
        private MissionStatus status; //도전가능,성공, 진행중 여부

    }
}
