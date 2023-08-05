package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QspotLikeEntity is a Querydsl query type for spotLikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QspotLikeEntity extends EntityPathBase<spotLikeEntity> {

    private static final long serialVersionUID = 1837226864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QspotLikeEntity spotLikeEntity = new QspotLikeEntity("spotLikeEntity");

    public final NumberPath<Integer> categoryNumber = createNumber("categoryNumber", Integer.class);

    public final QRstrntEntity rstrnt;

    public final NumberPath<Integer> spotLikeId = createNumber("spotLikeId", Integer.class);

    public final QTourspotEntity tourspot;

    public final QMemberEntity user;

    public QspotLikeEntity(String variable) {
        this(spotLikeEntity.class, forVariable(variable), INITS);
    }

    public QspotLikeEntity(Path<? extends spotLikeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QspotLikeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QspotLikeEntity(PathMetadata metadata, PathInits inits) {
        this(spotLikeEntity.class, metadata, inits);
    }

    public QspotLikeEntity(Class<? extends spotLikeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rstrnt = inits.isInitialized("rstrnt") ? new QRstrntEntity(forProperty("rstrnt")) : null;
        this.tourspot = inits.isInitialized("tourspot") ? new QTourspotEntity(forProperty("tourspot")) : null;
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user")) : null;
    }

}

