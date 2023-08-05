package com.greeneyback.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = -1408069967L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final NumberPath<Integer> challengeNum = createNumber("challengeNum", Integer.class);

    public final StringPath userBirthdate = createString("userBirthdate");

    public final StringPath userEmail = createString("userEmail");

    public final NumberPath<Integer> userGender = createNumber("userGender", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userNickname = createString("userNickname");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userPhonenum = createString("userPhonenum");

    public final StringPath userPicture = createString("userPicture");

    public final StringPath userTitle = createString("userTitle");

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

