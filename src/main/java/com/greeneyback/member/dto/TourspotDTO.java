package com.greeneyback.member.dto;

import com.greeneyback.member.entity.TourspotEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourspotDTO {

    private int tourspot_id;
    private int areaCode;
    private int sigunguCode;
    private String addr;
    private String mainimage;
    private String summary;
    private String tel;
    private String title;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private float  tourspotStar;

    public static TourspotDTO toTourspotDTO(TourspotEntity tourspotEntity) {
        TourspotDTO tourspotDTO = new TourspotDTO();
        tourspotDTO.setTourspot_id((tourspotEntity.getTourspotId()));
        tourspotDTO.setAreaCode(tourspotEntity.getAreaCode());
        tourspotDTO.setSigunguCode(tourspotEntity.getSigunguCode());
        tourspotDTO.setAddr(tourspotEntity.getAddr());
        tourspotDTO.setMainimage(tourspotEntity.getMainimage());
        tourspotDTO.setSummary(tourspotEntity.getSummary());
        tourspotDTO.setTel(tourspotEntity.getTel());
        tourspotDTO.setTitle(tourspotEntity.getTitle());
        tourspotDTO.setLatitude(tourspotEntity.getLatitude());
        tourspotDTO.setLongitude(tourspotEntity.getLongitude());
        tourspotDTO.setTourspotStar(tourspotEntity.getTourspotStar());

        return tourspotDTO;
    }

}