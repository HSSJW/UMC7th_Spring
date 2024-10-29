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
        @Column(name="store_id")
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

    }
