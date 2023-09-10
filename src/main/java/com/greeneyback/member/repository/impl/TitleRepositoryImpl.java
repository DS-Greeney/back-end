package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.TitleEntity;
import com.greeneyback.member.repository.custom.TitleRepositoryCustom;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.greeneyback.member.entity.QTitleEntity.titleEntity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TitleRepositoryImpl implements TitleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 사용자의 현재 칭호 정보를 반환하는 함수
    @Override
    public TitleEntity findUserNowTitleByUserChallengeNum(int userChallengeNum) {
        TitleEntity nowTitleEntity = queryFactory
                .selectFrom(titleEntity)
                .where(titleEntity.goalChallengeNm.eq(
                        JPAExpressions.select(titleEntity.goalChallengeNm.max())
                                .from(titleEntity)
                                .where(titleEntity.goalChallengeNm.loe(userChallengeNum))
                ))
                .fetchOne();
        return nowTitleEntity;
    }

    // 사용자의 다음 칭호 정보를 반환하는 함수
    @Override
    public TitleEntity findUserNextTitleByUserChallengeNum(int userChallengeNum) {
        TitleEntity nextTitleEntity = queryFactory
                .selectFrom(titleEntity)
                .where(titleEntity.goalChallengeNm.eq(
                        JPAExpressions.select(titleEntity.goalChallengeNm.min())
                                .from(titleEntity)
                                .where(titleEntity.goalChallengeNm.gt(userChallengeNum))
                ))
                .fetchOne();
        return nextTitleEntity;
    }

    // 사용자가 보유한 칭호 리스트를 반환하는 함수
    @Override
    public List<TitleEntity> findUserTitleList(int userChallengeNum) {
        List<TitleEntity> userTitleList = queryFactory
                .selectFrom(titleEntity)
                .where(titleEntity.goalChallengeNm.loe(userChallengeNum))
                .fetch();
        return userTitleList;
    }
}
