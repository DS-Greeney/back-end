package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;

import java.util.HashMap;
import java.util.List;

public interface TourspotRepositoryCustom {
    List<TourspotEntity> findByLocation(HashMap<String, Double> myLocation);
}
