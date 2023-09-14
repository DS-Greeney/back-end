package com.greeneyback.member.repository;

import com.greeneyback.member.entity.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Integer> {
    List<ChallengeEntity> findAll();
}
