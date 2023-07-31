package com.greeneyback.member.controller;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public HashMap<String, Object> register(String userNickname, String userEmail,
                                            String userPassword, String userPhonenum,
                                            String userBirthdate, Integer userGender) {

        HashMap<String, Object> userMap = new HashMap<>();

        try {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserNickname(userNickname);
            memberDTO.setUserEmail(userEmail);
            memberDTO.setUserPassword(userPassword);
            memberDTO.setUserPhonenum(userPhonenum);
            memberDTO.setUserBirthdate(userBirthdate);
            memberDTO.setUserGender(userGender);

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

    // 수정필요~~~~~~~~~~~~~~~~
    @PostMapping("/login")
    public HashMap<String, Object> login(String userEmail, String userPassword) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserEmail(userEmail);
            memberDTO.setUserPassword(userPassword);

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

}
