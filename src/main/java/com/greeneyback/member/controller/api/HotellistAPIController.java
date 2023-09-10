package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.HotelEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.service.AWSS3Service;
import com.greeneyback.member.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greeney/main")
@RequiredArgsConstructor
@Slf4j
public class HotellistAPIController {

    private final HotelService hotelService;
    private final AWSS3Service awss3Service;

    @GetMapping("/hotellist")
    public Object hotel(@RequestParam(name = "latitude", defaultValue = "37.5538") String latitude,
                             @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude,
                             @RequestParam(name = "areaCode") int areaCode) {
        HashMap<String, Object> hotelMap = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();

        if (areaCode == 0) {
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                hotelMap.put("success", Boolean.TRUE);
                hotelMap.put("hotels", hotelService.findByMyLocation(myLocation));
            } catch (Exception e) {
                hotelMap.put("error", e.getMessage());
            }
        } else {
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                hotelMap.put("success", Boolean.TRUE);
                hotelMap.put("hotels", hotelService.findByMyLocationAreaFilter(myLocation, areaCode));
            } catch (Exception e) {
                hotelMap.put("error", e.getMessage());
            }
        }

        return hotelMap;
    }

    @GetMapping("/hotellist/detail/{hotelId}")
    public HashMap<String, Object> getHotelDetail(@PathVariable int hotelId) {
        HashMap<String, Object> map = new HashMap<>();
        // review들을 모은 List
        List<Object> reviewList = new ArrayList<>();

        try {
            Optional<HotelEntity> hotel = hotelService.findHotelDetail(hotelId);

            // 리뷰 불러오기
            reviewList = hotelService.getReviewList(hotelId, 3);
            map.put("reviewList", reviewList);
            map.put("hotel", hotel);
            map.put("success", Boolean.TRUE);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 리뷰 작성 post
    @PostMapping("/hotellist/detail/{hotelspotId}")
    public HashMap<String, Object> postHotelComment(@RequestParam("images") List<MultipartFile> multipartFiles, @ModelAttribute CommentDTO commentDTO) {
        HashMap<String, Object> map = new HashMap<>();

        // S3 service

        try {
            // S3에 넣고 결과 url을 담을 리스트
            List<String> imageUrlList = new ArrayList<>();

            // S3Service에게 파일값을 넘겨주고 결과 url 리스트를 받아온다
            imageUrlList = awss3Service.uploadFiletToS3(multipartFiles);

            // 리뷰 테이블에 먼저 추가한 후 그 Entity를 받아온다. (commentID 때문에)
            SpotCommentEntity spotCommentEntity = hotelService.saveHotelReviewComment(commentDTO);

            // commentID와 함께 이미지 db를 추가한다.
            hotelService.saveHotelReviewImage(spotCommentEntity, imageUrlList);
            map.put("success", Boolean.TRUE);

        }
        catch(Exception e) {
            e.printStackTrace();
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

}
