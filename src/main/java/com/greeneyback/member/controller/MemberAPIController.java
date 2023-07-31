package com.greeneyback.member.controller;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class MemberAPIController {

    private final MemberService memberService;

    @PostMapping("/register")
    public HashMap<String, Object> register(@RequestParam String userNickname, @RequestParam String userEmail,
                                            @RequestParam String userPassword, @RequestParam String userPhonenum,
                                            @RequestParam String userBirthdate, @RequestParam Integer userGender) {

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

}
