package com.greeneyback.member.repository;

import com.greeneyback.member.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
}
