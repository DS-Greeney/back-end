package com.greeneyback.member.controller.api;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.SpotCommentImageEntity;
import com.greeneyback.member.service.MemberService;
import com.greeneyback.member.service.SpotCmntImgService;
import com.greeneyback.member.service.SpotCmntService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class SpotCommentAPIController {

    @Autowired
    private final MemberService memberService;
    private final SpotCmntService spotCmntService;
    private final SpotCmntImgService spotCmntImgService;

    @GetMapping("/myReviewList")
    public HashMap<String, Object> showMyReviewList(@RequestParam int userId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            List<Object> myReviewList = new ArrayList<>();

            Optional<MemberEntity> user = memberService.findUserById(Long.valueOf(userId));
            List<SpotCommentEntity> userReviewList = spotCmntService.findByUser(user.get());

            for (SpotCommentEntity spotReview : userReviewList) {
                HashMap<String, Object> map2 = new HashMap<>();

                int spotCmntId = spotReview.getSpotCmntId();
                int categoryNumber = spotReview.getCategoryNumber();
                String spotCmntContent = spotReview.getSpotCmntContent();
                int spotCmntStar = spotReview.getSpotCmntStar();
                LocalDate spotCmntTime = spotReview.getSpotCmntTime();
                int spotId = spotReview.getSpotId();

                List<String> imgUrlList = new ArrayList<>();
                List<SpotCommentImageEntity> spotImageEntityList = spotCmntImgService.findBySpotCmnt(spotReview);

                for (SpotCommentImageEntity spotCommentImageEntity : spotImageEntityList) {
                    imgUrlList.add(spotCommentImageEntity.getSpotImgUrl());
                }

                map2.put("spotCmntId", spotCmntId);
                map2.put("categoryNumber", categoryNumber);
                map2.put("spotCmntContent", spotCmntContent);
                map2.put("spotCmntStar", spotCmntStar);
                map2.put("spotCmntTime", spotCmntTime);
                map2.put("spotId", spotId);
                map2.put("spotCmntImg", imgUrlList);

                myReviewList.add(map2);
            }

            map.put("myReviewList", myReviewList);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }
}
