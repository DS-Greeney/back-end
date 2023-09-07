package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSpotCommentEntity is a Querydsl query type for SpotCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpotCommentEntity extends EntityPathBase<SpotCommentEntity> {

    private static final long serialVersionUID = 1204892204L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotCommentEntity spotCommentEntity = new QSpotCommentEntity("spotCommentEntity");

    public final NumberPath<Integer> categoryNumber = createNumber("categoryNumber", Integer.class);

    public final StringPath spotCmntContent = createString("spotCmntContent");

    public final NumberPath<Integer> spotCmntId = createNumber("spotCmntId", Integer.class);

    public final NumberPath<Integer> spotCmntStar = createNumber("spotCmntStar", Integer.class);

    public final DatePath<java.time.LocalDate> spotCmntTime = createDate("spotCmntTime", java.time.LocalDate.class);

    public final NumberPath<Integer> spotId = createNumber("spotId", Integer.class);

    public final QMemberEntity user;

    public QSpotCommentEntity(String variable) {
        this(SpotCommentEntity.class, forVariable(variable), INITS);
    }

    public QSpotCommentEntity(Path<? extends SpotCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSpotCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSpotCommentEntity(PathMetadata metadata, PathInits inits) {
        this(SpotCommentEntity.class, metadata, inits);
    }

    public QSpotCommentEntity(Class<? extends SpotCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user")) : null;
    }

}

