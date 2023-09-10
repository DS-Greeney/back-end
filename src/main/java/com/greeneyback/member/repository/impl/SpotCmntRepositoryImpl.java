package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.custom.SpotCmntRepositoryCustom;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.greeneyback.member.entity.QSpotCommentEntity.spotCommentEntity;

@Repository
@RequiredArgsConstructor
public class SpotCmntRepositoryImpl implements SpotCmntRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<SpotCommentEntity> findBySpotIdAndCategoryNumber(int spotId, int categoryNumber) {

        List<SpotCommentEntity> spotEntityList = queryFactory
                .selectFrom(spotCommentEntity)
                .where(spotCommentEntity.spotId.eq(spotId).and(spotCommentEntity.categoryNumber.eq(categoryNumber)))
                .fetch();
        return spotEntityList;

    }
}
