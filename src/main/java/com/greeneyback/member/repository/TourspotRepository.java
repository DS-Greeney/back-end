package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.custom.TourspotRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourspotRepository extends JpaRepository<TourspotEntity,Integer> , TourspotRepositoryCustom {

    // 조건없이 테이블의 전체 레코드 조회
    List<TourspotEntity> findAll();

    TourspotEntity findByTourspotId(int tourspotId);
}
