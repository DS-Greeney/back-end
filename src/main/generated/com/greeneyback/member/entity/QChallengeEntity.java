package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChallengeEntity is a Querydsl query type for ChallengeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallengeEntity extends EntityPathBase<ChallengeEntity> {

    private static final long serialVersionUID = -1066332654L;

    public static final QChallengeEntity challengeEntity = new QChallengeEntity("challengeEntity");

    public final StringPath challengeContent = createString("challengeContent");

    public final NumberPath<Integer> challengeId = createNumber("challengeId", Integer.class);

    public QChallengeEntity(String variable) {
        super(ChallengeEntity.class, forVariable(variable));
    }

    public QChallengeEntity(Path<? extends ChallengeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChallengeEntity(PathMetadata metadata) {
        super(ChallengeEntity.class, metadata);
    }

}

