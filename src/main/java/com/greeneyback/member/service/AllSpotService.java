package com.greeneyback.member.service;


import com.greeneyback.member.entity.AllSpotEntity;
import com.greeneyback.member.repository.impl.AllSpotRepositoryIml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllSpotService {

    private final AllSpotRepositoryIml allSpotRepositoryIml;



    public List<AllSpotEntity> findBySearchAndMyLocationAndCategoryNumber(List<String> stringList, HashMap<String, Double> myLocation, int categoryNumber) {

        // categoryNumber가 0인 경우 전체에서 검색
        if (categoryNumber == 0) {
            return allSpotRepositoryIml.findBySearchAndMyLocation(stringList,myLocation);
        }
        // 아니라면 filtering
        return allSpotRepositoryIml.findFilteringBySearchAndMyLocation(stringList, myLocation, categoryNumber);
    }

}
