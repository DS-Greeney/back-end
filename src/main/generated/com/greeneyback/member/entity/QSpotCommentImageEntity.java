package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSpotCommentImageEntity is a Querydsl query type for SpotCommentImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpotCommentImageEntity extends EntityPathBase<SpotCommentImageEntity> {

    private static final long serialVersionUID = -1297022859L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSpotCommentImageEntity spotCommentImageEntity = new QSpotCommentImageEntity("spotCommentImageEntity");

    public final QSpotCommentEntity spotCmnt;

    public final NumberPath<Integer> spotImgId = createNumber("spotImgId", Integer.class);

    public final StringPath spotImgUrl = createString("spotImgUrl");

    public QSpotCommentImageEntity(String variable) {
        this(SpotCommentImageEntity.class, forVariable(variable), INITS);
    }

    public QSpotCommentImageEntity(Path<? extends SpotCommentImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSpotCommentImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSpotCommentImageEntity(PathMetadata metadata, PathInits inits) {
        this(SpotCommentImageEntity.class, metadata, inits);
    }

    public QSpotCommentImageEntity(Class<? extends SpotCommentImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.spotCmnt = inits.isInitialized("spotCmnt") ? new QSpotCommentEntity(forProperty("spotCmnt"), inits.get("spotCmnt")) : null;
    }

}

