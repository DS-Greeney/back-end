package com.greeneyback.member.dto;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.entity.TourspotEntity;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpotLikeDTO {

    private int spotLikeId;
    private int categoryNumber;
    private int spotId;
    MemberEntity user;

    public static SpotLikeDTO toSpotLikeDTO(SpotLikeEntity spotLikeEntity) {
        SpotLikeDTO spotLikeDTO = new SpotLikeDTO();

        spotLikeDTO.setSpotLikeId(spotLikeEntity.getSpotLikeId());
        spotLikeDTO.setCategoryNumber(spotLikeEntity.getCategoryNumber());
        spotLikeDTO.setSpotId(spotLikeEntity.getSpotId());
        spotLikeDTO.setUser(spotLikeEntity.getUser());

        return spotLikeDTO;
    }
}
