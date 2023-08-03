package com.greeneyback.member.entity;

import com.greeneyback.member.dto.TourspotDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "tourspot_table")
public class TourspotEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false, name = "tourspot_id")
    private int tourspot_id;
    @Column
    private int areaCode;
    @Column
    private int sigunguCode;
    @Column
    @NotNull
    private String addr;
    @Column
    private String mainimage;
    @Column
    @NotNull
    private String summary;
    @Column
    @NotNull
    private String tel;
    @Column
    @NotNull
    private String title;
    @Column(length = 20)
    @NotNull
    private String latitude;
    @Column(length = 20)
    @NotNull
    private String longitude;

    public static TourspotEntity toTourspotEntity(TourspotDTO tourspotDTO) {
        TourspotEntity tourspotEntity = new TourspotEntity();
        tourspotEntity.setTourspot_id((tourspotDTO.getTourspot_id()));
        tourspotEntity.setAreaCode(tourspotDTO.getAreaCode());
        tourspotEntity.setSigunguCode(tourspotDTO.getSigunguCode());
        tourspotEntity.setAddr(tourspotDTO.getAddr());
        tourspotEntity.setMainimage(tourspotDTO.getMainimage());
        tourspotEntity.setSummary(tourspotDTO.getSummary());
        tourspotEntity.setTel(tourspotDTO.getTel());
        tourspotEntity.setTitle(tourspotDTO.getTitle());
        tourspotEntity.setLatitude(tourspotDTO.getLatitude());
        tourspotEntity.setLongitude(tourspotDTO.getLongitude());

        return tourspotEntity;
    }
}
