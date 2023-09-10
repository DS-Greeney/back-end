package com.greeneyback.member.entity;

import com.greeneyback.member.dto.ChallengeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "challenge_table")
public class ChallengeEntity {
    @Id
    @Column(unique = true)
    @NotNull
    private int challengeId;

    @Column(length = 200)
    @NotNull
    private String challengeContent;


    public static ChallengeEntity toChallengeEntity(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = new ChallengeEntity();
        challengeEntity.setChallengeId(challengeDTO.getChallengeId());
        challengeEntity.setChallengeContent(challengeDTO.getChallengeContent());
        return challengeEntity;
    }
}
