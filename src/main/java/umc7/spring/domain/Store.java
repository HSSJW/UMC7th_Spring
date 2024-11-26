package umc7.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public class Store extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        @ManyToOne
        @JoinColumn(name = "region_id")
        private Region region;

        @Column(nullable = false, length = 50)
        private String name;

        @Column(nullable = false, length = 50)
        private String address;
        private Float score;

        @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
        private List<Mission> missionList = new ArrayList<>();

        @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
        private List<Review> reviewList = new ArrayList<>();

        //간단하게 콘솔에 로그를 찍어주기 위해서, 도메인 코드에 toString() 메서드를 재정의할게요.
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") + // region의 이름 출력
                '}';
        }
    }
