package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.AllSpotEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.custom.AllSpotRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.greeneyback.member.entity.QAllSpotEntity.allSpotEntity;

@Repository
@RequiredArgsConstructor
public class AllSpotRepositoryIml implements AllSpotRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 전체 검색
    @Override
    public List<AllSpotEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation) {
        BooleanBuilder whereConditions = new BooleanBuilder();

        // string 별로 address, spot_name, spot_info에서 검색
        for (String search : stringList) {
            // 각 검색어에 대한 OR 조건 생성
            BooleanExpression condition = allSpotEntity.address.like("%" + search + "%")
                    .or(allSpotEntity.spotName.like("%" + search + "%"))
                    .or(allSpotEntity.spotInfo.like("%" + search + "%"));

            // 각 조건을 AND로 묶어 whereConditions에 추가
            whereConditions.and(condition);
        }
        List<AllSpotEntity> allSpotEntityList = queryFactory
                .selectFrom(allSpotEntity)
                .where(whereConditions)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                allSpotEntity.longitude,
                                allSpotEntity.latitude
                        )
                ).asc())
                .fetch();
        return allSpotEntityList;
    }

    @Override
    public List<AllSpotEntity> findFilteringBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation, int categoryNumber) {
        BooleanBuilder whereConditions = new BooleanBuilder();

        // string 별로 address, spot_name, spot_info에서 검색
        for (String search : stringList) {
            // 각 검색어에 대한 OR 조건 생성
            BooleanExpression condition = allSpotEntity.address.like("%" + search + "%")
                    .or(allSpotEntity.spotName.like("%" + search + "%"))
                    .or(allSpotEntity.spotInfo.like("%" + search + "%"))
                    .and(allSpotEntity.categoryNumber.eq(categoryNumber));

            // 각 조건을 OR로 묶어 whereConditions에 추가
            whereConditions.or(condition);
        }
        List<AllSpotEntity> allSpotEntityList = queryFactory
                .selectFrom(allSpotEntity)
                .where(whereConditions)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                allSpotEntity.longitude,
                                allSpotEntity.latitude
                        )
                ).asc())
                .fetch();
        return allSpotEntityList;
    }
}
