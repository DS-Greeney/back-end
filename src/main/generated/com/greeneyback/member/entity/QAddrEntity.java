package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddrEntity is a Querydsl query type for AddrEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddrEntity extends EntityPathBase<AddrEntity> {

    private static final long serialVersionUID = 366073800L;

    public static final QAddrEntity addrEntity = new QAddrEntity("addrEntity");

    public final StringPath addrId = createString("addrId");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final StringPath areaName = createString("areaName");

    public final NumberPath<Integer> sigunguCode = createNumber("sigunguCode", Integer.class);

    public final StringPath sigunguName = createString("sigunguName");

    public QAddrEntity(String variable) {
        super(AddrEntity.class, forVariable(variable));
    }

    public QAddrEntity(Path<? extends AddrEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddrEntity(PathMetadata metadata) {
        super(AddrEntity.class, metadata);
    }

}

