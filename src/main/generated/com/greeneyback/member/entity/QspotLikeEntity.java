package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSpotLikeEntity is a Querydsl query type for SpotLikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpotLikeEntity extends EntityPathBase<SpotLikeEntity> {

    private static final long serialVersionUID = 835233680L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotLikeEntity spotLikeEntity = new QSpotLikeEntity("spotLikeEntity");

    public final NumberPath<Integer> categoryNumber = createNumber("categoryNumber", Integer.class);

    public final QRstrntEntity rstrnt;

    public final NumberPath<Integer> spotLikeId = createNumber("spotLikeId", Integer.class);

    public final QTourspotEntity tourspot;

    public final QMemberEntity user;

    public QSpotLikeEntity(String variable) {
        this(SpotLikeEntity.class, forVariable(variable), INITS);
    }

    public QSpotLikeEntity(Path<? extends SpotLikeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSpotLikeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSpotLikeEntity(PathMetadata metadata, PathInits inits) {
        this(SpotLikeEntity.class, metadata, inits);
    }

    public QSpotLikeEntity(Class<? extends SpotLikeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rstrnt = inits.isInitialized("rstrnt") ? new QRstrntEntity(forProperty("rstrnt")) : null;
        this.tourspot = inits.isInitialized("tourspot") ? new QTourspotEntity(forProperty("tourspot")) : null;
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user")) : null;
    }

}

