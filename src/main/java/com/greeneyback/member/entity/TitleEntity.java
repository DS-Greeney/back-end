package com.greeneyback.member.entity;

import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.dto.TitleDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "title_table")
public class TitleEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false)
    private int titleId; // 칭호 아이디

    @Column(unique = true, nullable = false)
    private String titleName; // 칭호 이름

    @Column(nullable = false)
    private int goalChallengeNm; // 칭호를 달성하기 위한 총 도전과제 개수

    @Column(nullable = false)
    private int nextChallengeNm; // 다음 칭호를 달성하기 위해 완료해야할 도전과제 개수

    public static TitleEntity toTitleEntity(TitleDTO titleDTO) {
        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setTitleId(titleDTO.getTitleId());
        titleEntity.setTitleName(titleDTO.getTitleName());
        titleEntity.setGoalChallengeNm(titleDTO.getGoalChallengeNm());
        titleEntity.setNextChallengeNm(titleDTO.getNextChallengeNm());

        return titleEntity;
    }
}
