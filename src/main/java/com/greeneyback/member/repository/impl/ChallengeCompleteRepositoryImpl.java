package com.greeneyback.member.repository.impl;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.ChallengeCompleteEntity;
import com.greeneyback.member.repository.custom.ChallengeCompleteRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.greeneyback.member.entity.QChallengeCompleteEntity.challengeCompleteEntity;

import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class ChallengeCompleteRepositoryImpl implements ChallengeCompleteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChallengeCompleteEntity> findByUserIdAndChallengeId(Long userId, int challengeId) {
        return Optional.ofNullable(queryFactory.selectFrom(challengeCompleteEntity)
                .where(challengeCompleteEntity.userId.eq(userId).and(challengeCompleteEntity.challengeId.eq(challengeId)))
                .fetchOne());
    }

}
