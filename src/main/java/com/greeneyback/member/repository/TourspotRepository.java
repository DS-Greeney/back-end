package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourspotRepository extends CrudRepository<TourspotEntity,Integer> {

    // 조건없이 테이블의 전체 레코드 조회
    List<TourspotEntity> findAll();

}
