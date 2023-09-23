package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.ChallengeEntity;
import com.greeneyback.member.entity.TitleEntity;
import com.greeneyback.member.service.ChallengeService;
import com.greeneyback.member.service.MemberService;
import com.greeneyback.member.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class ChallengeAPIController {

    @Autowired
    private final MemberService memberService;
    private final TitleService titleService;
    private final ChallengeService challengeService;

    @PostMapping("/challengeComplete")
    public HashMap<String, Object> challengeComplete(@RequestParam int userId) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO user = memberService.findById((long) userId); // 현재 user가 누구인지 검색
            user.setChallengeNum(user.getChallengeNum()+1);         // user의 challenge개수를 +1 해준다.

            memberService.update(user);

            map.put("success", Boolean.TRUE);
            map.put("complete", 1);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }
        return map;
    }


    // 사용자의 도전과제 현황 및 칭호 정보 불러오기
    @GetMapping("/challengeInfo")
    public HashMap<String, Object> challenge(@RequestParam int userId) {
        HashMap<String, Object> map = new HashMap<>();
        int userChallengeNum = 0;

        try {
            MemberDTO user = memberService.findById((long) userId); // 현재 user가 누구인지 검색
            userChallengeNum = user.getChallengeNum();

            // 사용자의 현재 칭호 정보 불러오기
            TitleEntity userNowTitle = titleService.findNowUserTitle(userChallengeNum);

            // 사용자의 다음 칭호 정보 불러오기
            TitleEntity userNextTitle = titleService.findNextUserTitle(userChallengeNum);

            // 사용자의 칭호 리스트 불러오기
            List<String> userTitleList = titleService.findUserTitleList(userChallengeNum);

            // front에게 넘겨줄 값들
            map.put("success", true);
            map.put("userChallengeNum", user.getChallengeNum()); // 달성한 도전과제 수
            map.put("userRemainChallengeNum", userNextTitle.getGoalChallengeNm()-user.getChallengeNum()); // 다음 칭호까지 사용자의 남은 도전과제 수
            map.put("userNowTitle", user.getUserTitle());  // 사용자의 현재 칭호
            map.put("goal", userNextTitle.getGoalChallengeNm()); // 다음 칭호까지 남은 도전과제 수
            map.put("userTitleList", userTitleList);  // 사용자의 칭호 리스트



        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }
        return map;

    }
    
    // 칭호 변경
    @PostMapping("/updateTitle")
    public HashMap<String, Object> updateTitle(@RequestParam int userId, @RequestParam String title) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO user = memberService.findById((long) userId); // 현재 user가 누구인지 검색
            user.setUserTitle(title);

            memberService.update(user);

            map.put("success", Boolean.TRUE);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }
        return map;
        
    }

    // 랜덤으로 하루에 챌린지 3개 불러오기
    @GetMapping("/challenge/today")
    public HashMap<String, Object> todayRandomChallenge() {
        HashMap<String, Object> map = new HashMap<>();


        try {
            List<ChallengeEntity> randomChallenges = challengeService.getRandomChallenges();
            List<Object> challenges = new ArrayList<>();

            for (ChallengeEntity challenge : randomChallenges) {

                HashMap<String, Object> map2 = new HashMap<>();

                int challengeId = challenge.getChallengeId();
                String challengeContent = challenge.getChallengeContent();

                map2.put("challengeId", challengeId);
                map2.put("content", challengeContent);
                map2.put("complete", 0);

                challenges.add(map2);
            }

            map.put("todayChallengeList", challenges);
            map.put("suceess", Boolean.TRUE);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }
}
