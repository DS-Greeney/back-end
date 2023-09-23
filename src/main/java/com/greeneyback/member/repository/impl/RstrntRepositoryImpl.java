package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.custom.RstrntRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.greeneyback.member.entity.QRstrntEntity.rstrntEntity;


@Repository
@RequiredArgsConstructor
public class RstrntRepositoryImpl implements RstrntRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RstrntEntity> findByLocation(HashMap<String, Double> myLocation) {
        List<RstrntEntity> rstrntEntityList = queryFactory
                .selectFrom(rstrntEntity)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                rstrntEntity.rstrntLo,
                                rstrntEntity.rstrntLa
                        )
                ).asc())
                .fetch();
        return rstrntEntityList;
    }

    public List<RstrntEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode) {
        List<RstrntEntity> rstrntEntityListFiltering = queryFactory
                .selectFrom(rstrntEntity)
                .where(rstrntEntity.areaCode.eq(areaCode))
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                rstrntEntity.rstrntLo,
                                rstrntEntity.rstrntLa
                        )
                ).asc())
                .fetch();
        return rstrntEntityListFiltering;
    }

    @Override
    public List<RstrntEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation) {
        BooleanBuilder whereConditions = new BooleanBuilder();

        // string 별로 rstrnt_addr, rstrnt_ctgry, rstrnt_menuinfo, rstrnt_name에서 검색
        for (String search : stringList) {
            // 각 검색어에 대한 OR 조건 생성
            BooleanExpression condition = rstrntEntity.rstrntAddr.like("%" + search + "%")
                    .or(rstrntEntity.rstrntCtgry.like("%" + search + "%"))
                    .or(rstrntEntity.rstrntMenuinfo.like("%" + search + "%"))
                    .or(rstrntEntity.rstrntName.like("%" + search + "%"));

            // 각 조건을 OR로 묶어 whereConditions에 추가
            whereConditions.or(condition);
        }
        List<RstrntEntity> RstrntEntityList = queryFactory
                .selectFrom(rstrntEntity)
                .where(whereConditions)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                rstrntEntity.rstrntLo,
                                rstrntEntity.rstrntLa
                        )
                ).asc())
                .fetch();
        return RstrntEntityList;
    }
}
