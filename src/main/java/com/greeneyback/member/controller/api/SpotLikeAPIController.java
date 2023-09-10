package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.dto.SpotLikeDTO;
import com.greeneyback.member.entity.*;
import com.greeneyback.member.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class SpotLikeAPIController {

    @Autowired
    private final SpotLikeService spotLikeService;
    private final MemberService memberService;
    private final TourService tourService;
    private final RstrntService rstrntService;
    private final HotelService hotelService;

    @PostMapping("/like")
    public HashMap<String, Object> spotLike(@RequestParam int userId, @RequestParam int itemId) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            SpotLikeDTO spotLikeDTO = new SpotLikeDTO();

            Optional<MemberEntity> likeUser = memberService.findUserById(Long.valueOf(userId));
            spotLikeDTO.setUser(likeUser.get());

            Optional<TourspotEntity> likeTourspot = tourService.findById(itemId);
            Optional<RstrntEntity> likeRstrnt = rstrntService.findById(itemId);
            Optional<HotelEntity> likeHotel = hotelService.findById(itemId);
            // 여행코스

            if (likeTourspot.isPresent()) {
                spotLikeDTO.setCategoryNumber(1);
            }
            else if (likeRstrnt.isPresent()) {
                spotLikeDTO.setCategoryNumber(2);
            }
            else if (likeHotel.isPresent()) {
                spotLikeDTO.setCategoryNumber(3);
            }
            // 여행코스

            spotLikeDTO.setSpotId(itemId);
            map.put("likeSpotId", itemId);

            spotLikeService.saveSpotLike(spotLikeDTO);

            map.put("success", Boolean.TRUE);

            return map;

        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
            return map;
        }

    }

    @GetMapping("/like/{userId}")
    public HashMap<String, Object> getLikeList(@PathVariable int userId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            List<Object> likeList = new ArrayList<>();
            Optional<MemberEntity> user = memberService.findUserById(Long.valueOf(userId));
            List<SpotLikeEntity> spotLikes = spotLikeService.findByUser(user.get());

            for (SpotLikeEntity like : spotLikes) {
                int spotId = like.getSpotId();
                int categoryNumber = like.getCategoryNumber();

                HashMap<String, Object> map2 = new HashMap<>();

                map2.put("spotLikeId", like.getSpotLikeId());
                map2.put("categoryNumber", categoryNumber);

                if (categoryNumber==1) { // 관광지
                    Optional<TourspotEntity> likeTourspot = tourService.findById(spotId);
                    map2.put("spotLike", likeTourspot.get());
                    likeList.add(map2);
                }
                else if (categoryNumber==2) { // 식당
                    Optional<RstrntEntity> likeRstrnt = rstrntService.findById(spotId);
                    map2.put("spotLike", likeRstrnt.get());
                    likeList.add(map2);
                }
                else if (categoryNumber==3) { // 호텔
                    Optional<HotelEntity> likeHotel = hotelService.findById(spotId);
                    map2.put("spotLike", likeHotel.get());
                    likeList.add(map2);
                }
                // 여행코스
            }

            map.put("spotLikeList", likeList);
            map.put("success", Boolean.TRUE);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    @Transactional
    @DeleteMapping("/dislike")
    public HashMap<String, Object> deleteLike(@RequestParam int categoryNumber, @RequestParam int spotId, @RequestParam Long userId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
//            spotLikeService.deleteSpotLikeById(spotLikeId);
//            map.put("dislikeSpotId", spotLikeId);

            // categoryNumber와, spotId와, userId를 이용해서 값을 찾기
            spotLikeService.deleteSpotLikeByCategoryNumAndSpotIdAndUserId(categoryNumber, spotId, userId);
            map.put("success", Boolean.TRUE);
        } catch(Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

}
