package com.greeneyback.member.controller;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) { // session - 로그인정보를 유지
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공 - main페이지로
            session.setAttribute("loginId", loginResult.getUserId());
            session.setAttribute("loginEmail", loginResult.getUserEmail()); // 로그인한 유저의 이메일 정보를 세션에 담아준다, loginEmail로 사용
            session.setAttribute("loginNickname", loginResult.getUserNickname());
            return "main";
        } else {
            // 로그인 실패(return값이 null) - 다시 로그인 페이지로
            return "login";
        }
    }
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateUser", memberDTO);
        return "update";
    }

    @PostMapping("member/update") // 변경요청
    public String update(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        memberService.update(memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);
        session.setAttribute("loginNickname", loginResult.getUserNickname());
        return "main";
    }
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            session.setAttribute("loginNickname", loginResult.getUserNickname());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }


    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
    @GetMapping("/member/delete")
    public String deleteById(HttpSession session, Model model) {
        Long loginId = (Long) session.getAttribute("loginId");
        memberService.deleteById(loginId);
        return "delete";
    }
}
