package com.greeneyback.member.service;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.entity.ChallengeEntity;
import com.greeneyback.member.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    public void challengeSave(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = ChallengeEntity.toChallengeEntity(challengeDTO);
        challengeRepository.save(challengeEntity);
    }

    // 도전과제 랜덤 3개 생성
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public List<ChallengeEntity> getRandomChallenges() {
        List<ChallengeEntity> allChallenges = challengeRepository.findAll();

        List<ChallengeEntity> randomChallenges = new ArrayList<>();
        int totalChallenges = allChallenges.size();

        Random random = new Random();
        for (int i=0; i<3; i++) {
            int randomIndex = random.nextInt(totalChallenges);
            ChallengeEntity challengeEntity = allChallenges.get(randomIndex);
            randomChallenges.add(challengeEntity);
        }

        return randomChallenges;
    }
}
