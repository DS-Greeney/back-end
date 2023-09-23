package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAllSpotEntity is a Querydsl query type for AllSpotEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAllSpotEntity extends EntityPathBase<AllSpotEntity> {

    private static final long serialVersionUID = -1878007054L;

    public static final QAllSpotEntity allSpotEntity = new QAllSpotEntity("allSpotEntity");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final NumberPath<Integer> categoryNumber = createNumber("categoryNumber", Integer.class);

    public final NumberPath<Integer> dataCode = createNumber("dataCode", Integer.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final NumberPath<Integer> spotId = createNumber("spotId", Integer.class);

    public final StringPath spotImage = createString("spotImage");

    public final StringPath spotInfo = createString("spotInfo");

    public final StringPath spotName = createString("spotName");

    public QAllSpotEntity(String variable) {
        super(AllSpotEntity.class, forVariable(variable));
    }

    public QAllSpotEntity(Path<? extends AllSpotEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllSpotEntity(PathMetadata metadata) {
        super(AllSpotEntity.class, metadata);
    }

}

