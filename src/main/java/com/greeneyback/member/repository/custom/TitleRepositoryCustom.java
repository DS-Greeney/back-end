package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.TitleEntity;

import java.util.List;

public interface TitleRepositoryCustom {

    // 사용자의 현재 칭호 정보를 반환하는 함수
    TitleEntity findUserNowTitleByUserChallengeNum(int userChallengeNum);

    // 사용자의 다음 칭호 정보를 반환하는 함수
    TitleEntity findUserNextTitleByUserChallengeNum(int userChallengeNum);

    // 사용자가 보유한 칭호 리스트를 반환하는 함수
    List<TitleEntity> findUserTitleList(int userChallengeNum);

}
