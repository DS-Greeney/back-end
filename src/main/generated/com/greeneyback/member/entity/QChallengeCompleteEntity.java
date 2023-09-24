package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChallengeCompleteEntity is a Querydsl query type for ChallengeCompleteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallengeCompleteEntity extends EntityPathBase<ChallengeCompleteEntity> {

    private static final long serialVersionUID = 649854379L;

    public static final QChallengeCompleteEntity challengeCompleteEntity = new QChallengeCompleteEntity("challengeCompleteEntity");

    public final NumberPath<Integer> challengeId = createNumber("challengeId", Integer.class);

    public final NumberPath<Integer> complete_Id = createNumber("complete_Id", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QChallengeCompleteEntity(String variable) {
        super(ChallengeCompleteEntity.class, forVariable(variable));
    }

    public QChallengeCompleteEntity(Path<? extends ChallengeCompleteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChallengeCompleteEntity(PathMetadata metadata) {
        super(ChallengeCompleteEntity.class, metadata);
    }

}

