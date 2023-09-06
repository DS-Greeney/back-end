package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotCommentEntity;
import com.greeneyback.member.entity.TourspotImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourspotImgRepository extends JpaRepository<TourspotImageEntity, Integer> {
    List<TourspotImageEntity> findByTourspotCmnt(TourspotCommentEntity tourspotCommentEntity);
}
