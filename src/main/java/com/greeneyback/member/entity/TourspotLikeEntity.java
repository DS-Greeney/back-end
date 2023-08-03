package com.greeneyback.member.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "tourspot_like_table")
public class TourspotLikeEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourspotLikeId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "tourspot_id")
    TourspotEntity tourspot;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id")
    MemberEntity user;
}
