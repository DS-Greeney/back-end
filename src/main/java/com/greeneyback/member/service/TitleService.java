package com.greeneyback.member.service;

import com.greeneyback.member.dto.TitleDTO;
import com.greeneyback.member.entity.TitleEntity;
import com.greeneyback.member.repository.TitleRepository;
import com.greeneyback.member.repository.impl.TitleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    private final TitleRepositoryImpl titleRepositoryImpl;
    public void titleSave(TitleDTO titleDTO) {
        TitleEntity titleEntity = TitleEntity.toTitleEntity(titleDTO);
        titleRepository.save(titleEntity);
    }

    public Optional<TitleEntity> findById(int id) {
        return titleRepository.findById(id);
    }

    public TitleEntity findNowUserTitle(int userChallengeNum) {
        return titleRepositoryImpl.findUserNowTitleByUserChallengeNum(userChallengeNum);
    }

    public TitleEntity findNextUserTitle(int userChallengeNum) {
        return titleRepositoryImpl.findUserNextTitleByUserChallengeNum(userChallengeNum);
    }
}
