package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "restaurant_image_table")
public class RstrntImgEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rstrntImageId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "rstrnt_id")
    RstrntEntity rstrnt;

    @Column(length = 500)
    private String rstrntImage;
}
