package com.greeneyback.member.service;

import com.greeneyback.member.dto.TitleDTO;
import com.greeneyback.member.entity.TitleEntity;
import com.greeneyback.member.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    public void titleSave(TitleDTO titleDTO) {
        TitleEntity titleEntity = TitleEntity.toTitleEntity(titleDTO);
        titleRepository.save(titleEntity);
    }
}
