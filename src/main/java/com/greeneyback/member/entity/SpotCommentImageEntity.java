package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "spot_image_table")
public class SpotCommentImageEntity {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spotImgId; // 이미지 고유 아이디

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = SpotCommentEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "spot_cmnt_id", name= "spot_cmnt_id")
    SpotCommentEntity spotCmnt;

    @Column(length = 300)
    private String spotImgUrl; // 이미지 url


}