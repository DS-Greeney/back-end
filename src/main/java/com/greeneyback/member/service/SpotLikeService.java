package com.greeneyback.member.service;

import com.greeneyback.member.dto.SpotLikeDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.repository.SpotLikeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.greeneyback.member.entity.QSpotLikeEntity.spotLikeEntity;

@Service
@RequiredArgsConstructor
public class SpotLikeService {

    @Autowired
    private SpotLikeRepository spotLikeRepository;
    private final JPAQueryFactory queryFactory;

    public List<SpotLikeEntity> findByUser(MemberEntity user) {
        return spotLikeRepository.findByUser(user);
    }

    public void saveSpotLike(SpotLikeDTO spotLikeDTO) {
        SpotLikeEntity spotLikeEntity = SpotLikeEntity.toSpotLikeEntity(spotLikeDTO);
        spotLikeRepository.save(spotLikeEntity);
    }

    public void deleteSpotLikeById(int spotLikeId) {
        queryFactory.delete(spotLikeEntity)
                .where(spotLikeEntity.spotLikeId.eq(spotLikeId))
                        .execute();
    }

}
