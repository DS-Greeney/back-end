package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTourspotEntity is a Querydsl query type for TourspotEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourspotEntity extends EntityPathBase<TourspotEntity> {

    private static final long serialVersionUID = 664098417L;

    public static final QTourspotEntity tourspotEntity = new QTourspotEntity("tourspotEntity");

    public final StringPath addr = createString("addr");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final StringPath mainimage = createString("mainimage");

    public final NumberPath<Integer> sigunguCode = createNumber("sigunguCode", Integer.class);

    public final StringPath summary = createString("summary");

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> tourspot_id = createNumber("tourspot_id", Integer.class);

    public QTourspotEntity(String variable) {
        super(TourspotEntity.class, forVariable(variable));
    }

    public QTourspotEntity(Path<? extends TourspotEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTourspotEntity(PathMetadata metadata) {
        super(TourspotEntity.class, metadata);
    }

}

