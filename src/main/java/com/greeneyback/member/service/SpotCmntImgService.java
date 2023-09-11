package com.greeneyback.member.service;

import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.SpotCommentImageEntity;
import com.greeneyback.member.repository.SpotCmntImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotCmntImgService {

    @Autowired
    SpotCmntImgRepository spotCmntImgRepository;

    public List<SpotCommentImageEntity> findBySpotCmnt(SpotCommentEntity spotCommentEntity) {
        return spotCmntImgRepository.findBySpotCmnt(spotCommentEntity);
    }
}
