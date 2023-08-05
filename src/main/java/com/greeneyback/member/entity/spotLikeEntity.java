package com.greeneyback.member.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "spot_like_table")
public class spotLikeEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spotLikeId;

    // 관광지 : 1 , 식당 : 2 , 숙소 : 3, 여행코스 : 4
    @Column
    @NotNull
    private int categoryNumber; // 좋아요 누른 카테고리 코드

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "tourspot_id")
    TourspotEntity tourspot;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "rstrnt_id")
    RstrntEntity rstrnt;

    // 숙소 id  - 추후 수정

    // 여행코스 id  - 추후 수정

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id")
    MemberEntity user;
}
