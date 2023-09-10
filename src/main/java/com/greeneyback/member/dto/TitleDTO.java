package com.greeneyback.member.dto;

import com.greeneyback.member.entity.TitleEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TitleDTO {
    private int titleId;
    private String titleName;
    private int goalChallengeNm;
    private int nextChallengeNm;

    public static TitleDTO toTitleDTO(TitleEntity titleEntity) {
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setTitleId(titleEntity.getTitleId());
        titleDTO.setTitleName(titleEntity.getTitleName());
        titleDTO.setGoalChallengeNm(titleEntity.getGoalChallengeNm());
        titleDTO.setNextChallengeNm(titleEntity.getNextChallengeNm());

        return titleDTO;
    }
}
