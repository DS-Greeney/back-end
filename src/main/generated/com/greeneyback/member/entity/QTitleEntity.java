package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTitleEntity is a Querydsl query type for TitleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTitleEntity extends EntityPathBase<TitleEntity> {

    private static final long serialVersionUID = -1571652025L;

    public static final QTitleEntity titleEntity = new QTitleEntity("titleEntity");

    public final NumberPath<Integer> goalChallengeNm = createNumber("goalChallengeNm", Integer.class);

    public final NumberPath<Integer> nextChallengeNm = createNumber("nextChallengeNm", Integer.class);

    public final NumberPath<Integer> titleId = createNumber("titleId", Integer.class);

    public final StringPath titleName = createString("titleName");

    public QTitleEntity(String variable) {
        super(TitleEntity.class, forVariable(variable));
    }

    public QTitleEntity(Path<? extends TitleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTitleEntity(PathMetadata metadata) {
        super(TitleEntity.class, metadata);
    }

}

