package com.greeneyback.member.controller.api;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.service.RstrntService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class RstrntlistAPIController {

    @Autowired
    private final RstrntService rstrntService;

    @GetMapping("restaurantlist")
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

    @GetMapping("/restaurantlist/detail/{rstrntId}")
    public HashMap<String, Object> getRestaurantlistDetail(@PathVariable String rstrntId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            Optional<RstrntEntity> restaurant = rstrntService.findRstrntDetail(rstrntId);
            map.put("success", Boolean.TRUE);
            map.put("restaurant", restaurant);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }
}
