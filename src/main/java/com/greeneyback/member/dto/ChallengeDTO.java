package com.greeneyback.member.dto;

import com.greeneyback.member.entity.ChallengeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChallengeDTO {
    private int challengeId;
    private String challengeContent;

    public static ChallengeDTO toChallengeDTO(ChallengeEntity challengeEntity) {
        ChallengeDTO challengeDTO = new ChallengeDTO();
        challengeDTO.setChallengeId(challengeEntity.getChallengeId());
        challengeDTO.setChallengeContent(challengeEntity.getChallengeContent());
        return challengeDTO;
    }
}
