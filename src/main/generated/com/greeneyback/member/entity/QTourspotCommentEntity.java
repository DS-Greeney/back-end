package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTourspotCommentEntity is a Querydsl query type for TourspotCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourspotCommentEntity extends EntityPathBase<TourspotCommentEntity> {

    private static final long serialVersionUID = -847680300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTourspotCommentEntity tourspotCommentEntity = new QTourspotCommentEntity("tourspotCommentEntity");

    public final QTourspotEntity tourspot;

    public final StringPath tourspotCmntContent = createString("tourspotCmntContent");

    public final NumberPath<Integer> tourspotCmntId = createNumber("tourspotCmntId", Integer.class);

    public final NumberPath<Integer> tourspotCmntStar = createNumber("tourspotCmntStar", Integer.class);

    public final DatePath<java.time.LocalDate> tourspotCmntTime = createDate("tourspotCmntTime", java.time.LocalDate.class);

    public final QMemberEntity user;

    public QTourspotCommentEntity(String variable) {
        this(TourspotCommentEntity.class, forVariable(variable), INITS);
    }

    public QTourspotCommentEntity(Path<? extends TourspotCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTourspotCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTourspotCommentEntity(PathMetadata metadata, PathInits inits) {
        this(TourspotCommentEntity.class, metadata, inits);
    }

    public QTourspotCommentEntity(Class<? extends TourspotCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tourspot = inits.isInitialized("tourspot") ? new QTourspotEntity(forProperty("tourspot")) : null;
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user")) : null;
    }

}

