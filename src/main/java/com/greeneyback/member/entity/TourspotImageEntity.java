package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "tourspot_image_table")
public class TourspotImageEntity {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourspotImgId; // 이미지 고유 아이디

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "tourspotCmntId", name= "tourspot_cmnt_id")
    TourspotCommentEntity tourspotCmntId;

    @Column(length = 300)
    private String tourspotImgUrl; // 이미지 url


}