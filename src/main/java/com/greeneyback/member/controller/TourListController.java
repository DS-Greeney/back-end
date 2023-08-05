package com.greeneyback.member.controller;

import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourListController {

    @Autowired
    private final TourService tourService;

    // tourList 반환 메서드
    @GetMapping("/greeney/main/tourlist1")
    public Object tourlist(@RequestBody HashMap<String, Double> myLocation) {

        HashMap<String, Object> tourMap = new HashMap<>();
//        HashMap<String, Double> myLocation = new HashMap<>();
//
//        // 더미데이터
//        myLocation.put("longitude", 126.9019532);
//        myLocation.put("latitude", 37.5170112);

        try {
            tourMap.put("success", Boolean.TRUE);
            tourMap.put("tourlists", tourService.findByMyLocation(myLocation));
        } catch (Exception e) {
            tourMap.put("error", e.getMessage());
        }

        return tourMap;
    }
}
