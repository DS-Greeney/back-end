package com.greeneyback.member.service;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.entity.ChallengeEntity;
import com.greeneyback.member.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    public void challengeSave(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = ChallengeEntity.toChallengeEntity(challengeDTO);
        challengeRepository.save(challengeEntity);
    }
}
