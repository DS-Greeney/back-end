package com.greeneyback.member.service;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.ChallengeCompleteEntity;
import com.greeneyback.member.entity.ChallengeEntity;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.repository.ChallengeCompleteRepository;
import com.greeneyback.member.repository.ChallengeRepository;
import com.greeneyback.member.repository.impl.ChallengeCompleteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeCompleteRepository challengeCompleteRepository;
    private final ChallengeCompleteRepositoryImpl challengeCompleteRepositoryImpl;
    private List<ChallengeEntity> randomChallenges = new ArrayList<>();

    @Autowired
    private final ChallengeRepository challengeRepository;
    public void challengeSave(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = ChallengeEntity.toChallengeEntity(challengeDTO);
        challengeRepository.save(challengeEntity);
    }


    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void generateRandomChallenges() {

        // challengeCompleteTable 모두 지우기
        challengeCompleteRepository.deleteAll();

        List<ChallengeEntity> allChallenges = challengeRepository.findAll();

        int totalChallenges = allChallenges.size();

        Random random = new Random();
        randomChallenges.clear(); // 이전 랜덤 과제를 지우고 새로 생성
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(totalChallenges);
            ChallengeEntity challengeEntity = allChallenges.get(randomIndex);
            randomChallenges.add(challengeEntity);
        }
    }

    public List<ChallengeEntity> getRandomChallenges() {
        return randomChallenges;
    }

    // challenge 완료했다면 table에 넣어주기
    public void updateChallengeComplete(Long userId, int challengeId) {
        ChallengeCompleteEntity challengeCompleteEntity = new ChallengeCompleteEntity();
        challengeCompleteEntity.setUserId(userId);
        challengeCompleteEntity.setChallengeId(challengeId);
        challengeCompleteRepository.save(challengeCompleteEntity);
    }

    // challenge 했는지 결과보기
    public int getResultChallengeComplete(Long userId, int challengeId) {

        // 결과 보기!
        Optional<ChallengeCompleteEntity> result = challengeCompleteRepositoryImpl.findByUserIdAndChallengeId(userId, challengeId);
        if (!result.isPresent()) {   // 안 했음.
            return 0;
        }
        return 1;
    }

    public ChallengeDTO findById(int challengeId) {
        Optional<ChallengeEntity> challengeEntity = challengeRepository.findById(challengeId);
        if (challengeEntity.isPresent()) {
            return ChallengeDTO.toChallengeDTO(challengeEntity.get());
        } else {
            return null;
        }
    }
}
