package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHotelEntity is a Querydsl query type for HotelEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHotelEntity extends EntityPathBase<HotelEntity> {

    private static final long serialVersionUID = -59365213L;

    public static final QHotelEntity hotelEntity = new QHotelEntity("hotelEntity");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final StringPath hotelAddr = createString("hotelAddr");

    public final NumberPath<Integer> hotelId = createNumber("hotelId", Integer.class);

    public final StringPath hotelInfo = createString("hotelInfo");

    public final NumberPath<java.math.BigDecimal> hotelLa = createNumber("hotelLa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> hotelLo = createNumber("hotelLo", java.math.BigDecimal.class);

    public final StringPath hotelName = createString("hotelName");

    public final StringPath hotelService = createString("hotelService");

    public final NumberPath<Float> hotelStar = createNumber("hotelStar", Float.class);

    public final StringPath hotelTel = createString("hotelTel");

    public final StringPath hotelUrl = createString("hotelUrl");

    public QHotelEntity(String variable) {
        super(HotelEntity.class, forVariable(variable));
    }

    public QHotelEntity(Path<? extends HotelEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHotelEntity(PathMetadata metadata) {
        super(HotelEntity.class, metadata);
    }

}

