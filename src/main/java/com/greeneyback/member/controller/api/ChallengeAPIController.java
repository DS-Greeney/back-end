package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.TitleEntity;
import com.greeneyback.member.service.MemberService;
import com.greeneyback.member.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class ChallengeAPIController {

    @Autowired
    private final MemberService memberService;
    private final TitleService titleService;

    @PostMapping("/challengeComplete")
    public HashMap<String, Object> challenge(@RequestParam int userId, @RequestParam int challengeId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO user = memberService.findById((long) userId);
            user.setChallengeNum(user.getChallengeNum()+1);
            
            int challengeNum = user.getChallengeNum();
            String title = "";
            int remainChallNum = 0;
            Optional<TitleEntity> titleEntity = null;

            if (0<=challengeNum && challengeNum<3) { // 1단계일 때
                titleEntity = titleService.findById(2); // 다음 목표 2단계 설정
                remainChallNum = titleEntity.get().getGoalChallengeNm()-challengeNum;
            }
            else if (3<=challengeNum && challengeNum<10) { // 2단계일 때
                titleEntity = titleService.findById(2);
                title = titleEntity.get().getTitleName();
                user.setUserTitle(title);
                titleEntity = titleService.findById(3); // 다음 목표 3단계 설정
                remainChallNum = titleEntity.get().getGoalChallengeNm()-(challengeNum-titleService.findById(2).get().getGoalChallengeNm());
                // 다음 단계까지 남은 챌린지 개수 = 다음 단계의 목표 챌린지 개수 - (지금까지 쌓인 챌린지 개수 - 전 단계 목표 챌린지 개수)
            }

            memberService.update(user);

            map.put("challengeNum", user.getChallengeNum());
            map.put("remainChallNum", remainChallNum);
            map.put("title", user.getUserTitle());
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }


        return map;
    }
}