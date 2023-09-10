package com.greeneyback.member.repository.impl;

import com.greeneyback.member.repository.custom.SpotLikeRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// 이 부분 자꾸 오류나 으아아러아렁잘머;ㄴ이ㅏ럼ㄴ이ㅏ왜이럼???????????? 이거랑 밑에 함수 내용 주석처리하면 오류안남
import static com.greeneyback.member.entity.QSpotLikeEntity.spotLikeEntity;

@Repository
@RequiredArgsConstructor
public class SpotLikeRepositoryImpl implements SpotLikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteSpotLikeByCategoryNumAndSpotIdAndUserId(int categoryNumber, int spotId, Long userId) {

        queryFactory.delete(spotLikeEntity)
                .where(spotLikeEntity.categoryNumber.eq(categoryNumber).and(spotLikeEntity.spotId.eq(spotId)).and(spotLikeEntity.user.userId.eq(userId)))
                .execute();
    }
}
