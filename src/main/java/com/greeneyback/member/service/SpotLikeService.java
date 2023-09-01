package com.greeneyback.member.service;

import com.greeneyback.member.dto.SpotLikeDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.repository.SpotLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotLikeService {

    @Autowired
    private SpotLikeRepository spotLikeRepository;

    public List<SpotLikeEntity> findByUser(MemberEntity user) {
        return spotLikeRepository.findByUser(user);
    }

    public void saveSpotLike(SpotLikeDTO spotLikeDTO) {
        SpotLikeEntity spotLikeEntity = SpotLikeEntity.toSpotLikeEntity(spotLikeDTO);
        spotLikeRepository.save(spotLikeEntity);
    }

    public void deleteSpotLikeById(int spotLikeId) {
        spotLikeRepository.deleteById(spotLikeId);
    }

}
