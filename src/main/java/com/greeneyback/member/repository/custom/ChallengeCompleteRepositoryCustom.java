package com.greeneyback.member.repository.custom;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.ChallengeCompleteEntity;

import java.util.Optional;

public interface ChallengeCompleteRepositoryCustom {
    Optional<ChallengeCompleteEntity> findByUserIdAndChallengeId(Long userId, int challengeId);
}
