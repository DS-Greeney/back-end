package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourspotRepository extends JpaRepository<TourspotEntity,Integer> , TourspotRepositoryCustom {

    // 조건없이 테이블의 전체 레코드 조회
    List<TourspotEntity> findAll();

}
