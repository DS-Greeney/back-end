package com.greeneyback.member.controller;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class MemberAPIController {

    @Autowired
    private final MemberService memberService;

    @PostMapping("/register")
    public HashMap<String, Object> register(@RequestBody MemberDTO memberDTO) {

        HashMap<String, Object> userMap = new HashMap<>();

        try {
//            MemberDTO memberDTO = new MemberDTO();
//            memberDTO.setUserNickname(userNickname);
//            memberDTO.setUserEmail(userEmail);
//            memberDTO.setUserPassword(userPassword);
//            memberDTO.setUserPhonenum(userPhonenum);
//            memberDTO.setUserBirthdate(userBirthdate);
//            memberDTO.setUserGender(userGender);

            memberService.save(memberDTO);

            userMap.put("success", Boolean.TRUE);

            log.info("회원가입 성공");

            return userMap;
        } catch (Exception e) {
            userMap.put("success", Boolean.FALSE);
            userMap.put("message", e.getMessage());

            log.info("회원가입 실패");
            return userMap;
        }
    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody MemberDTO memberDTO) {

        HashMap<String, Object> map = new HashMap<>();

        try {
//            MemberDTO memberDTO = new MemberDTO();
//            memberDTO.setUserEmail(userEmail);
//            memberDTO.setUserPassword(userPassword);

            MemberDTO loginResult = memberService.login(memberDTO);

            map.put("success", Boolean.TRUE);
            map.put("userId", loginResult.getUserId());

            log.info("로그인 성공");

            return map;
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());

            log.info("로그인 실패");
            return map;
        }
    }

    @GetMapping("/logout")
    public HashMap<String, Object> logout(HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();

        session.invalidate();

        map.put("success", Boolean.TRUE);

        return map;
    }

    // 수정필요 **********************
    @GetMapping("/delete")
    public HashMap<String, Object> deleteById(HttpSession session, Model model) {
        HashMap<String, Object> map = new HashMap<>();

        Long loginId = (Long) session.getAttribute("loginId");
        memberService.deleteById(loginId);

        map.put("success", Boolean.TRUE);

        return map;
    }

    @PostMapping("/info")
    public HashMap<String, Object> userInfo(@RequestBody MemberDTO memberDTO) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO user = memberService.findById(memberDTO.getUserId());
            map.put("success", Boolean.TRUE);
            map.put("userId", user.getUserId());
            map.put("userNickname", user.getUserNickname());
            map.put("userEmail", user.getUserEmail());
            map.put("userPassword", user.getUserPassword());
            map.put("userPhonenum", user.getUserPhonenum());
            map.put("userBirthdate", user.getUserBirthdate());
            map.put("userGender", user.getUserGender());
            map.put("userTitle", user.getUserTitle());
            map.put("userPicture", user.getUserPicture());
            map.put("ChallengeNum", user.getChallengeNum());
            // 내가 쓴 글
            // 내가 댓글 단 글
            // 내가 쓴 후기
            // 찜한 목록
            // 달성한 도전 과제
            return map;
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());

            log.info("회원정보 요청 실패");
            return map;
        }
    }


}
