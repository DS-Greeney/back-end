package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotCommentEntity;
import com.greeneyback.member.entity.TourspotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourspotCmntRepository extends JpaRepository<TourspotCommentEntity, Integer> {
    List<TourspotCommentEntity> findByTourspot(TourspotEntity tourspotEntity);

}
