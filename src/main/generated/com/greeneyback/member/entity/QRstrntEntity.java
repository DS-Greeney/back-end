package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRstrntEntity is a Querydsl query type for RstrntEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRstrntEntity extends EntityPathBase<RstrntEntity> {

    private static final long serialVersionUID = -99835300L;

    public static final QRstrntEntity rstrntEntity = new QRstrntEntity("rstrntEntity");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final StringPath rstrntAddr = createString("rstrntAddr");

    public final StringPath rstrntCtgry = createString("rstrntCtgry");

    public final StringPath rstrntId = createString("rstrntId");

    public final StringPath rstrntLa = createString("rstrntLa");

    public final StringPath rstrntLo = createString("rstrntLo");

    public final StringPath rstrntMenuinfo = createString("rstrntMenuinfo");

    public final StringPath rstrntName = createString("rstrntName");

    public final NumberPath<Integer> rstrntStar = createNumber("rstrntStar", Integer.class);

    public final StringPath rstrntTel = createString("rstrntTel");

    public QRstrntEntity(String variable) {
        super(RstrntEntity.class, forVariable(variable));
    }

    public QRstrntEntity(Path<? extends RstrntEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRstrntEntity(PathMetadata metadata) {
        super(RstrntEntity.class, metadata);
    }

}

