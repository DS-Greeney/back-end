package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.service.AWSS3Service;
import com.greeneyback.member.service.RstrntService;
import com.greeneyback.member.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class TourlistAPIController {

    @Autowired
    private final TourService tourService;

    private final AWSS3Service awss3Service;


    // tourList 반환 메서드
    @GetMapping("/tourlist")
    public Object tourlist(@RequestParam(name = "latitude", defaultValue = "37.5538") String latitude,
                           @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude,
                            @RequestParam(name = "areaCode") int areaCode) {
    //디폴트위도경도값:서울중심(남산)

        HashMap<String, Object> tourMap = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();
//        HashMap<String, Double> myLocation = new HashMap<>();
//
//        // 더미데이터
//        myLocation.put("longitude", 126.9019532);
//        myLocation.put("latitude", 37.5170112);


        // areaCode = 0 전체를 쏴준다.
        if(areaCode == 0) {
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                tourMap.put("success", Boolean.TRUE);
                tourMap.put("tourlists", tourService.findByMyLocation(myLocation));
            } catch (Exception e) {
                tourMap.put("error", e.getMessage());
            }
        } else {  // areaCode filtering 한 것
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                tourMap.put("success", Boolean.TRUE);
                tourMap.put("tourlists", tourService.findByMyLocationAreaFilter(myLocation, areaCode));
            } catch (Exception e) {
                tourMap.put("error", e.getMessage());
            }
        }
        return tourMap;
    }

    @GetMapping("/tourlist/detail/{tourspotId}")
    public HashMap<String, Object> getTourlistDetail(@PathVariable int tourspotId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            Optional<TourspotEntity> tourspot = tourService.findTourspotDetail(tourspotId);
            map.put("success", Boolean.TRUE);
            map.put("tourspot", tourspot);

            // 리뷰 불러오기 추후..^^



        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 리뷰 작성 post
    @PostMapping("/tourlist/detail/{tourspotId}")
    public ResponseEntity<?> postTourComment(@RequestParam("images") List<MultipartFile> multipartFiles, @ModelAttribute CommentDTO commentDTO) {

        // S3 service

        try {
            List<String> imageUrlList = new ArrayList<>();
            imageUrlList = awss3Service.uploadFiletToS3(multipartFiles);
            tourService.saveTourReviewComment(commentDTO, imageUrlList);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

}
