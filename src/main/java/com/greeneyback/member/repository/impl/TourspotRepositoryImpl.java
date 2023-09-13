package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.custom.TourspotRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.greeneyback.member.entity.QTourspotEntity.tourspotEntity;

@Repository
@RequiredArgsConstructor
public class TourspotRepositoryImpl implements TourspotRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TourspotEntity> findByLocation(HashMap<String, Double> myLocation) {
        List<TourspotEntity> tourspotEntityList = queryFactory
                .selectFrom(tourspotEntity)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                tourspotEntity.longitude,
                                tourspotEntity.latitude
                        )
                ).asc())
                .fetch();
        return tourspotEntityList;
    }

    public List<TourspotEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode) {
        List<TourspotEntity> tourspotEntityListFiltering = queryFactory
                .selectFrom(tourspotEntity)
                .where(tourspotEntity.areaCode.eq(areaCode))
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                tourspotEntity.longitude,
                                tourspotEntity.latitude
                        )
                ).asc())
                .fetch();
        return tourspotEntityListFiltering;

    }

    @Override
    public List<TourspotEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation) {
        BooleanBuilder whereConditions = new BooleanBuilder();

        // string 별로 addr, summary, title에서 검색
        for (String search : stringList) {
            // 각 검색어에 대한 OR 조건 생성
            BooleanExpression condition = tourspotEntity.addr.like("%" + search + "%")
                    .or(tourspotEntity.summary.like("%" + search + "%"))
                    .or(tourspotEntity.title.like("%" + search + "%"));

            // 각 조건을 AND로 묶어 whereConditions에 추가
            whereConditions.and(condition);
        }
        List<TourspotEntity> tourspotEntityList = queryFactory
                .selectFrom(tourspotEntity)
                .where(whereConditions)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                tourspotEntity.longitude,
                                tourspotEntity.latitude
                        )
                ).asc())
                .fetch();
        return tourspotEntityList;
    }

}
