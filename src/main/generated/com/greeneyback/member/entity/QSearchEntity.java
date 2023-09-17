package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSearchEntity is a Querydsl query type for SearchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearchEntity extends EntityPathBase<SearchEntity> {

    private static final long serialVersionUID = -900634817L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSearchEntity searchEntity = new QSearchEntity("searchEntity");

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final StringPath searchContent = createString("searchContent");

    public final NumberPath<Integer> searchId = createNumber("searchId", Integer.class);

    public final DatePath<java.time.LocalDate> searchTime = createDate("searchTime", java.time.LocalDate.class);

    public final QMemberEntity user;

    public QSearchEntity(String variable) {
        this(SearchEntity.class, forVariable(variable), INITS);
    }

    public QSearchEntity(Path<? extends SearchEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSearchEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSearchEntity(PathMetadata metadata, PathInits inits) {
        this(SearchEntity.class, metadata, inits);
    }

    public QSearchEntity(Class<? extends SearchEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QMemberEntity(forProperty("user")) : null;
    }

}

