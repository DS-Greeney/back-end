package com.greeneyback.member.entity;

import com.greeneyback.member.dto.TourspotDTO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "tourspot_table")
public class TourspotEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false)
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

        return tourspotEntity;
    }
}
