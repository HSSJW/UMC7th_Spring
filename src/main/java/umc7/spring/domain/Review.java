package umc7.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private Float score;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래 키 컬럼명을 "member_id"로 지정
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
