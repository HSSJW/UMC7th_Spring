package umc7.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.FoodCategory;
import umc7.spring.domain.Member;
import umc7.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;
}