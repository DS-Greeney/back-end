package com.greeneyback.member.entity;


import com.greeneyback.member.dto.SpotLikeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "spot_like_table")
public class SpotLikeEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spotLikeId;

    // 관광지 : 1 , 식당 : 2 , 숙소 : 3, 여행코스 : 4
    @Column
    @NotNull
    private int categoryNumber; // 좋아요 누른 카테고리 코드

    @Column
    @NotNull
    private int spotId; // 장소 ID

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = MemberEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    MemberEntity user;

    public static SpotLikeEntity toSpotLikeEntity(SpotLikeDTO spotLikeDTO) {
        SpotLikeEntity spotLikeEntity = new SpotLikeEntity();
        spotLikeEntity.setSpotLikeId(spotLikeDTO.getSpotLikeId());
        spotLikeEntity.setCategoryNumber(spotLikeDTO.getCategoryNumber());
        spotLikeEntity.setSpotId(spotLikeDTO.getSpotId());
        spotLikeEntity.setUser(spotLikeDTO.getUser());

        return spotLikeEntity;
    }
}
