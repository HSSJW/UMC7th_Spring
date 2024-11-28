package umc7.spring.web.dto.MissionDto;

import lombok.*;
import umc7.spring.domain.Mission;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MissionResponseDto {
    private Long id;
    private String content;
    private Integer reward;
    private LocalDate deadline;


    public MissionResponseDto(Mission mission) {
        this.id = mission.getId();
        this.content = mission.getContent();
        this.reward = mission.getReward();
        this.deadline = mission.getDeadline();

    }
}
