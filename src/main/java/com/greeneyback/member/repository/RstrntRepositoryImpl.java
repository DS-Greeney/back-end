package com.greeneyback.member.repository;

import com.greeneyback.member.entity.RstrntEntity;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.greeneyback.member.entity.QRstrntEntity.rstrntEntity;

@Repository
@RequiredArgsConstructor
public class RstrntRepositoryImpl implements RstrntRepositoryCustom{

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
}
