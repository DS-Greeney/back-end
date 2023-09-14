package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.RstrntEntity;

import java.util.HashMap;
import java.util.List;

public interface RstrntRepositoryCustom {

    List<RstrntEntity> findByLocation(HashMap<String, Double> myLocation);
    List<RstrntEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode);

    List<RstrntEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation);
}
