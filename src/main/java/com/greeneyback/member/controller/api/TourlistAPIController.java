package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.service.AWSS3Service;
import com.greeneyback.member.service.MemberService;
import com.greeneyback.member.service.SpotLikeService;
import com.greeneyback.member.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final MemberService memberService;
    private final SpotLikeService spotLikeService;
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
    public HashMap<String, Object> getTourlistDetail(@PathVariable int tourspotId, @RequestParam int userId) {
        // 결과를 담은 hashmap
        HashMap<String, Object> map = new HashMap<>();
        // review들을 모은 List
        List<Object> reviewList = new ArrayList<>();


        try {
            Optional<TourspotEntity> tourspot = tourService.findTourspotDetail(tourspotId);
            map.put("tourspot", tourspot);
            // 찜여부 추가해서 보냄  like 있으면 1, 없으면 0
//            Optional<MemberEntity> user = memberService.findUserById(Long.valueOf(userId));
//            List<SpotLikeEntity> spotLikes = spotLikeService.findByUser(user.get());
//
//            for (SpotLikeEntity like : spotLikes) {
//                if (like.getSpotId()==tourspotId) {
//                    map.put("like", 1);
//                }
//            }
//            map.put("like", 0);

            // 리뷰 불러오기
            reviewList = tourService.getReviewList(tourspotId, 1);
            map.put("reviewList", reviewList);
            map.put("success", Boolean.TRUE);

        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 리뷰 작성 post
    @PostMapping("/tourlist/detail/{tourspotId}")
    public HashMap<String, Object> postTourComment(@RequestParam("images") List<MultipartFile> multipartFiles, @ModelAttribute CommentDTO commentDTO) {
        HashMap<String, Object> map = new HashMap<>();

        // S3 service

        try {
            // S3에 넣고 결과 url을 담을 리스트
            List<String> imageUrlList = new ArrayList<>();

            // S3Service에게 파일값을 넘겨주고 결과 url 리스트를 받아온다
            imageUrlList = awss3Service.uploadFiletToS3(multipartFiles);

            // 리뷰 테이블에 먼저 추가한 후 그 Entity를 받아온다. (commentID 때문에)
            SpotCommentEntity spotCommentEntity = tourService.saveTourReviewComment(commentDTO);

            // commentID와 함께 이미지 db를 추가한다.
            tourService.saveTourReviewImage(spotCommentEntity, imageUrlList);
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
