package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRstrntImgEntity is a Querydsl query type for RstrntImgEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRstrntImgEntity extends EntityPathBase<RstrntImgEntity> {

    private static final long serialVersionUID = 2147339373L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRstrntImgEntity rstrntImgEntity = new QRstrntImgEntity("rstrntImgEntity");

    public final QRstrntEntity rstrnt;

    public final StringPath rstrntImage = createString("rstrntImage");

    public final NumberPath<Integer> rstrntImageId = createNumber("rstrntImageId", Integer.class);

    public QRstrntImgEntity(String variable) {
        this(RstrntImgEntity.class, forVariable(variable), INITS);
    }

    public QRstrntImgEntity(Path<? extends RstrntImgEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRstrntImgEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRstrntImgEntity(PathMetadata metadata, PathInits inits) {
        this(RstrntImgEntity.class, metadata, inits);
    }

    public QRstrntImgEntity(Class<? extends RstrntImgEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rstrnt = inits.isInitialized("rstrnt") ? new QRstrntEntity(forProperty("rstrnt")) : null;
    }

}

