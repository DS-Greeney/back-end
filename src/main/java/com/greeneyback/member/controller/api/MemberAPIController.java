package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.repository.MemberRepository;
import com.greeneyback.member.service.EncryptService;
import com.greeneyback.member.service.MailService;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class MemberAPIController {

    @Autowired
    private final MemberService memberService;
    private final EncryptService encryptService;
    private final MemberRepository memberRepository;
    private final MailService mailService;

    private int number; // 이메일 인증 숫자를 저장하는 변수

    @PostMapping("/signup")
    public HashMap<String, Object> signUp(@RequestBody MemberDTO memberDTO) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            memberService.registerUser(memberDTO);
            map.put("success", Boolean.TRUE);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    @PostMapping("/userLogin")
    public HashMap<String, Object> userLogin(@RequestBody MemberDTO memberDTO) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            Optional<MemberEntity> loginResult = memberService.userLogin(memberDTO);

            // 이메일 복호화 테스트 부분임
            String nickname = memberDTO.getUserNickname();
            Optional<MemberEntity> user = memberRepository.findByUserNickname(nickname);

            String decodedEmail = encryptService.decryptEmail(user.get().getUserEmail());

            map.put("success", Boolean.TRUE);
            map.put("userId", loginResult.get().getUserId());

            map.put("userEmail", decodedEmail);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());
        }

        return map;
    }

    @GetMapping("/checkUsername")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean isUnique = memberService.isUserNicknameUnique(username);
        return ResponseEntity.ok(isUnique);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean isUnique = memberService.isUserEmailUnique(email);
        return ResponseEntity.ok(isUnique);
    }

    @PostMapping("/mailSend")
    public HashMap<String, Object> mailSend(String mail) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            number = mailService.sendMail(mail);
            String num = String.valueOf(number);

            map.put("success", Boolean.TRUE);
            map.put("number", num);
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }

    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

        boolean isMatch = userNumber.equals(String.valueOf(number));

        return ResponseEntity.ok(isMatch);
    }


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

    @PostMapping("/delete")
    public HashMap<String, Object> deleteById(@RequestBody MemberDTO memberDTO) {
        HashMap<String, Object> map = new HashMap<>();

        Long id = memberDTO.getUserId();

        memberService.deleteById(id);

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

            String decodedEmail = encryptService.decryptEmail(user.getUserEmail());
            map.put("userEmail", decodedEmail);
//            map.put("userPhonenum", user.getUserPhonenum());
//            map.put("userBirthdate", user.getUserBirthdate());
//            map.put("userGender", user.getUserGender());
            map.put("userTitle", user.getUserTitle());
            map.put("userPicture", user.getUserPicture());

            return map;
        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());

            log.info("회원정보 요청 실패");
            return map;
        }
    }

    @PostMapping("/update/userNickname")
    public HashMap<String, Object> updateUserNicknameForm(@RequestBody MemberDTO updateUser) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            MemberDTO memberDTO = memberService.findById(updateUser.getUserId());

            memberDTO.setUserNickname(updateUser.getUserNickname());

            memberService.update(memberDTO);

            map.put("success", Boolean.TRUE);
            map.put("userNickname", memberDTO.getUserNickname());

            return map;

        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());

            log.info("회원 닉네임 수정 실패");
            return map;
        }
    }

    @PostMapping("/update/userPassword")
    public HashMap<String, Object> updateUserPasswordForm(@RequestBody MemberDTO updateUser) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            memberService.updateUserPassword(updateUser.getUserId(), updateUser.getUserPassword());

            map.put("success", Boolean.TRUE);

            return map;

        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());

            log.info("회원 비밀번호 수정 실패");
            return map;
        }
    }


    @GetMapping("/auth")
    public HashMap<String, Object> authUser(@ModelAttribute MemberDTO memberDTO) {
        HashMap<String, Object> map = new HashMap<>();



        return map;
    }


}
