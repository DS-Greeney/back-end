package com.greeneyback.member.controller;

import com.greeneyback.member.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class HomeController {

//    @Autowired
//    private final LocationService locationService;

    // 기본페이지 요청 메서드
    @GetMapping("/")
    public String index() {
        return "index"; // => templates 폴더의 index.html을 찾아감
    }

    // tourlist 반환 메서드
//    @GetMapping("/greeney/main/tourlist")
//    public Object tourlist(@RequestBody HashMap<String, String> myLocation) {
//
//        HashMap<String, Object> tourMap = new HashMap<>();
//
//        if(myLocation.get("latitude") != null || myLocation.get("longitude") != null) {
//            tourMap.put("success", Boolean.FALSE);
//            return tourMap;
//        }
//
//        // 1. repository를 통해 모든 List 가져오기
//
//        // 2. distanceList 구하기
//
//        // 3. rankList 구하기
//
//        // 4. 결론은 에바였다.
//
//
//
//        return tourMap;
//    }
}
