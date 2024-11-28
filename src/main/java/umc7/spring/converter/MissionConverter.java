package umc7.spring.converter;

import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.web.dto.MissionDto.MissionRequestDto;

public class MissionConverter {
    public static Mission toMission(MissionRequestDto request, Store store) {
        return Mission.builder()
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();
    }
}
