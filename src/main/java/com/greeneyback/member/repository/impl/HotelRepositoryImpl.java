package com.greeneyback.member.repository.impl;

import com.greeneyback.member.entity.HotelEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.custom.HotelRepositoryCustom;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.greeneyback.member.entity.QHotelEntity.hotelEntity;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryImpl implements HotelRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HotelEntity> findByLocation(HashMap<String, Double> myLocation) {
        List<HotelEntity> hotelEntityList = queryFactory
                .selectFrom(hotelEntity)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                hotelEntity.hotelLo,
                                hotelEntity.hotelLa
                        )
                ).asc())
                .fetch();
        return hotelEntityList;
    }

    @Override
    public List<HotelEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode) {
        List<HotelEntity> hotelEntityListFiltering = queryFactory
                .selectFrom(hotelEntity)
                .where(hotelEntity.areaCode.eq(areaCode))
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0}, {1})",
                        Expressions.stringTemplate("POINT({0}, {1})",
                                myLocation.get("longitude"),
                                myLocation.get("latitude")
                        ),
                        Expressions.stringTemplate("POINT({0}, {1})",
                                hotelEntity.hotelLo,
                                hotelEntity.hotelLa
                        )
                ).asc())
                .fetch();
        return hotelEntityListFiltering;
    }
}
