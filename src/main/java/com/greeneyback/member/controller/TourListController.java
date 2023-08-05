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
    @GetMapping("/greeney/main/tourlist")
    public Object tourlist() {

        HashMap<String, Object> tourMap = new HashMap<>();
        try {

            tourMap.put("tourlists", tourService.findBySigunguCode());
        } catch (Exception e) {
            tourMap.put("error", e.getMessage());
        }

        return tourMap;
    }
}
