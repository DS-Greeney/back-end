package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTourspotImageEntity is a Querydsl query type for TourspotImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourspotImageEntity extends EntityPathBase<TourspotImageEntity> {

    private static final long serialVersionUID = -241057968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTourspotImageEntity tourspotImageEntity = new QTourspotImageEntity("tourspotImageEntity");

    public final QTourspotCommentEntity tourspotCmntId;

    public final NumberPath<Integer> tourspotImgId = createNumber("tourspotImgId", Integer.class);

    public final StringPath tourspotImgUrl = createString("tourspotImgUrl");

    public QTourspotImageEntity(String variable) {
        this(TourspotImageEntity.class, forVariable(variable), INITS);
    }

    public QTourspotImageEntity(Path<? extends TourspotImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTourspotImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTourspotImageEntity(PathMetadata metadata, PathInits inits) {
        this(TourspotImageEntity.class, metadata, inits);
    }

    public QTourspotImageEntity(Class<? extends TourspotImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tourspotCmntId = inits.isInitialized("tourspotCmntId") ? new QTourspotCommentEntity(forProperty("tourspotCmntId"), inits.get("tourspotCmntId")) : null;
    }

}

