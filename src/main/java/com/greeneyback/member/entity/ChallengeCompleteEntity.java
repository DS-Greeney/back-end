package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "challenge_complete_table")
public class ChallengeCompleteEntity {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_complete_id")
    private int complete_Id; // 검색 아이디

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name="challenge_id")
    @NotNull
    private int challengeId;

}
