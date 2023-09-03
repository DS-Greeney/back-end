package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourspotCmntRepository extends JpaRepository<TourspotCommentEntity, Integer> {

}
