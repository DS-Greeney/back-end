package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.AllSpotEntity;

import java.util.HashMap;
import java.util.List;

public interface AllSpotRepositoryCustom {

    // 검색
    List<AllSpotEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation);

    // 검색, 카테고리 필터링
    List<AllSpotEntity> findFilteringBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation, int categoryNumber);
}
