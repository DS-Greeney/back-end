package com.greeneyback.member.controller.api;


import com.greeneyback.member.entity.AllSpotEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.service.AllSpotService;
import com.greeneyback.member.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class SearchAPIController {

    private final SearchService searchService;
    private final AllSpotService allSpotService;

    @GetMapping("search")
    public HashMap<String, Object> searchAllData(@RequestParam(name = "search") String search,
                                                 @RequestParam(name = "categoryNumber") int categoryNumber,
                                                  @RequestParam(name = "latitude", defaultValue = "37.5538") String latitude,
                                                  @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude,
                                                 @RequestParam(name = "userId") Long userId) {

        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();

        // 검색 결과
        try {
            // 내 위치 저장
            myLocation.put("latitude", Double.parseDouble(latitude));
            myLocation.put("longitude", Double.parseDouble(longitude));

            // search 작업해서 List로 받아오기
            List<String> stringList = searchService.stringToList(search);

            // searchTable에 userId, categoryNumber와 함께 저장
            searchService.saveSearch(stringList, userId, categoryNumber);

            // AllSpotService에 전달해 결과 받아오기
            List<AllSpotEntity> allSpotEntities = allSpotService.findBySearchAndMyLocationAndCategoryNumber(stringList, myLocation, categoryNumber);

            // 결과 저장
            result.put("spots", allSpotEntities);
            result.put("success", Boolean.TRUE);


        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", Boolean.FALSE);
            result.put("error", e.getMessage());
        }

        return result;

    }

}
