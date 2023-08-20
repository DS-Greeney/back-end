package com.greeneyback.member.controller;

import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.service.RstrntService;
import com.greeneyback.member.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class TourlistAPIController {

    @Autowired
    private final TourService tourService;
    @Autowired
    private final RstrntService rstrntService;

    // tourList 반환 메서드
    @GetMapping("/tourlist")
    public Object tourlist(@RequestParam(name = "latitude", defaultValue = "37.5538") String latitude, @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude) {
    //디폴트위도경도값:서울중심(남산)

        HashMap<String, Object> tourMap = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();
//        HashMap<String, Double> myLocation = new HashMap<>();
//
//        // 더미데이터
//        myLocation.put("longitude", 126.9019532);
//        myLocation.put("latitude", 37.5170112);

        try {
            myLocation.put("latitude", Double.parseDouble(latitude));
            myLocation.put("longitude", Double.parseDouble(longitude));
            tourMap.put("success", Boolean.TRUE);
            tourMap.put("tourlists", tourService.findByMyLocation(myLocation));
        } catch (Exception e) {
            tourMap.put("error", e.getMessage());
        }

        return tourMap;
    }

    @GetMapping("restaurant")
    public Object restaurant(@RequestParam(name = "latitude", defaultValue = "37.5538") String latitude, @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude) {
        HashMap<String, Object> tourMap = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();

        try {
            myLocation.put("latitude", Double.parseDouble(latitude));
            myLocation.put("longitude", Double.parseDouble(longitude));
            tourMap.put("success", Boolean.TRUE);
            tourMap.put("restaurants", rstrntService.findByMyLocation(myLocation));
        } catch (Exception e) {
            tourMap.put("error", e.getMessage());
        }

        return tourMap;
    }
}
