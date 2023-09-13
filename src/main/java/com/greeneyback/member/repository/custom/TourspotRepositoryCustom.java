package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.TourspotEntity;

import java.util.HashMap;
import java.util.List;

public interface TourspotRepositoryCustom {
    List<TourspotEntity> findByLocation(HashMap<String, Double> myLocation);
    List<TourspotEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode);

    List<TourspotEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation);
}
