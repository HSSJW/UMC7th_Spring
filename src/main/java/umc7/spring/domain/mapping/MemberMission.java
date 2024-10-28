package umc7.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.Member;
import umc7.spring.domain.Mission;
import umc7.spring.domain.common.BaseEntity;
import umc7.spring.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    //Member테이블과 연결 - 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    //Mission테이블과 연결 - 양방향 매핑
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}