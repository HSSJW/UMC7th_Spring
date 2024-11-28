package umc7.spring.converter;

import umc7.spring.domain.Member;
import umc7.spring.domain.Mission;
import umc7.spring.domain.enums.MissionStatus;
import umc7.spring.domain.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMission toMissionChallenge(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}

