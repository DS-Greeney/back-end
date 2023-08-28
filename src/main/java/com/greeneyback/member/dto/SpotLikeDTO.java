package com.greeneyback.member.dto;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.entity.TourspotEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpotLikeDTO {

    private int spotLikeId;
    private int categoryNumber;
    TourspotEntity tourspot;
    RstrntEntity rstrnt;
    MemberEntity user;

    public static SpotLikeDTO toSpotLikeDTO(SpotLikeEntity spotLikeEntity) {
        SpotLikeDTO spotLikeDTO = new SpotLikeDTO();

        spotLikeDTO.setSpotLikeId(spotLikeEntity.getSpotLikeId());
        spotLikeDTO.setCategoryNumber(spotLikeEntity.getCategoryNumber());
        spotLikeDTO.setTourspot(spotLikeEntity.getTourspot());
        spotLikeDTO.setRstrnt(spotLikeEntity.getRstrnt());
        spotLikeDTO.setUser(spotLikeEntity.getUser());

        return spotLikeDTO;
    }
}
