package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.HotelEntity;


import java.util.HashMap;
import java.util.List;

public interface HotelRepositoryCustom {

    List<HotelEntity> findByLocation(HashMap<String, Double> myLocation);
    List<HotelEntity> findByLocationAreaCode(HashMap<String, Double> myLocation, int areaCode);

    List<HotelEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation);
}
