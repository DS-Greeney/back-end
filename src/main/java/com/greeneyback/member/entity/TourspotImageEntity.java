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

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = TourspotCommentEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "tourspot_cmnt_id", name= "tourspot_cmnt_id")
    TourspotCommentEntity tourspotCmnt;

    @Column(length = 300)
    private String tourspotImgUrl; // 이미지 url


}