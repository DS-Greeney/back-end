package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.greeneyback.member.entity.QTourspotEntity.tourspotEntity;

@Repository
@RequiredArgsConstructor
public class TourspotRepositoryImpl implements TourspotRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public TourspotEntity findBySigunguCode(Integer code) {
        return queryFactory.selectFrom(tourspotEntity)
                .where(tourspotEntity.sigunguCode.eq(code))
                .fetchFirst();
    }
}
