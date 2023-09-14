package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.*;
import com.greeneyback.member.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
public class RstrntlistAPIController {

    @Autowired
    private final RstrntService rstrntService;
    private final MemberService memberService;
    private final SpotLikeService spotLikeService;
    private final AWSS3Service awss3Service;
    private final SearchService searchService;

    @GetMapping("/restaurantlist")
    public Object restaurant(@RequestParam(name = "latitude", defaultValue = "37.5538") String latitude,
                             @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude,
                             @RequestParam(name = "areaCode") int areaCode) {
        HashMap<String, Object> rstrntMap = new HashMap<>();
        HashMap<String, Double> myLocation = new HashMap<>();

        if (areaCode == 0) {
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                rstrntMap.put("success", Boolean.TRUE);
                rstrntMap.put("restaurants", rstrntService.findByMyLocation(myLocation));
            } catch (Exception e) {
                rstrntMap.put("error", e.getMessage());
            }
        } else {
            try {
                myLocation.put("latitude", Double.parseDouble(latitude));
                myLocation.put("longitude", Double.parseDouble(longitude));
                rstrntMap.put("success", Boolean.TRUE);
                rstrntMap.put("restaurants", rstrntService.findByMyLocationAreaFilter(myLocation, areaCode));
            } catch (Exception e) {
                rstrntMap.put("error", e.getMessage());
            }
        }

        return rstrntMap;
    }

    // 별점순 정렬
    @GetMapping("/restaurantlist/star")
    public HashMap<String, Object> restaurantlistOrderByStar() {
        HashMap<String, Object> map = new HashMap<>();

        try {
            List<RstrntEntity> restaurantlistOrderByStar = rstrntService.findAllOrderByStar();
            map.put("restaurantlist", restaurantlistOrderByStar);
            map.put("success", Boolean.TRUE);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    @GetMapping("/restaurantlist/detail/{rstrntId}")
    public HashMap<String, Object> getRestaurantlistDetail(@PathVariable int rstrntId, @RequestParam int userId) {
        HashMap<String, Object> map = new HashMap<>();
        // review들을 모은 List
        List<Object> reviewList = new ArrayList<>();

        try {
            Optional<RstrntEntity> restaurant = rstrntService.findRstrntDetail(rstrntId);

            // 찜 있으면 like 1, 없으면 0
            Optional<MemberEntity> user = memberService.findUserById((long) userId);
            List<SpotLikeEntity> spotLikes = spotLikeService.findByUser(user.get());

            map.put("like", 0);
            for (SpotLikeEntity like : spotLikes) {
                if (like.getSpotId()==rstrntId) {
                    map.put("like", 1);
                }
            }

            // 리뷰 불러오기
            reviewList = rstrntService.getReviewList(rstrntId, 2);
            map.put("reviewList", reviewList);
            map.put("restaurant", restaurant);
            map.put("success", Boolean.TRUE);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 리뷰 작성 post
    @Transactional
    @PostMapping("/restaurantlist/detail/{rstrntId}")
    public HashMap<String, Object> postRstrntComment(@RequestParam("images") List<MultipartFile> multipartFiles, @ModelAttribute CommentDTO commentDTO) {
        HashMap<String, Object> map = new HashMap<>();

        // S3 service

        try {
            // S3에 넣고 결과 url을 담을 리스트
            List<String> imageUrlList = new ArrayList<>();

            // S3Service에게 파일값을 넘겨주고 결과 url 리스트를 받아온다
            imageUrlList = awss3Service.uploadReviewImageFileToS3(multipartFiles);

            // 리뷰 테이블에 먼저 추가한 후 그 Entity를 받아온다. (commentID 때문에)
            SpotCommentEntity spotCommentEntity = rstrntService.saveRstrntReviewComment(commentDTO);

            // commentID와 함께 이미지 db를 추가한다.
            rstrntService.saveRstrntReviewImage(spotCommentEntity, imageUrlList);

            // 리뷰 별점을 계산하고 저장한다.
            rstrntService.calculateAvgStar(commentDTO);

            map.put("success", Boolean.TRUE);
        }
        catch(Exception e) {
            e.printStackTrace();
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    // 검색 결과 받아오는 메서드
    @GetMapping("/restaurantlist/{userId}")
    public HashMap<String, Object> searchTourlist(@PathVariable(name = "userId") Long userId,
                                                  @RequestParam(name = "latitude", defaultValue = "37.5538") String latitude,
                                                  @RequestParam(name = "longitude", defaultValue = "126.9916") String longitude,
                                                  @RequestParam(name = "search") String search) {
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
            searchService.saveSearch(stringList, userId, 2);

            // rstrntService에 전달해 결과 받아오기
            List<RstrntEntity> rstrntEntities = rstrntService.findBySearchAndMyLocation(stringList, myLocation);

            // 결과 저장
            result.put("restaurantlist", rstrntEntities);
            result.put("success", Boolean.TRUE);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", Boolean.FALSE);
            result.put("error", e.getMessage());
        }


        return result;
    }

}
