package com.greeneyback.member.repository;

import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.SpotCommentImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotCmntImgRepository extends JpaRepository<SpotCommentImageEntity, Integer> {
    List<SpotCommentImageEntity> findBySpotCmnt(SpotCommentEntity spotCommentEntity);
}
