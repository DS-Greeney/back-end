package com.greeneyback.member.repository;

import com.greeneyback.member.entity.RstrntEntity;

import java.util.HashMap;
import java.util.List;

public interface RstrntRepositoryCustom {

    List<RstrntEntity> findByLocation(HashMap<String, Double> myLocation);
}
