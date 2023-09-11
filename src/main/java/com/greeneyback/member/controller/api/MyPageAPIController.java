package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.service.AWSS3Service;
import com.greeneyback.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class MyPageAPIController {

    private final AWSS3Service awss3Service;
    private final MemberService memberService;

    // 프로필 이미지 변경 API
    @Transactional
    @PostMapping("/settings/profileImage/{userId}")
    public HashMap<String, Object> postUserProfileImage(@PathVariable Long userId, @RequestParam("image") MultipartFile multipartFiles) {
        HashMap<String, Object> map = new HashMap<>();

        // S3 service

        try {
            // S3에 넣고 결과 url을 담을 String
            String imageUrl = null;

            // S3Service에게 파일값을 넘겨주고 결과 url를 받아온다
            imageUrl = awss3Service.uploadUserProfileImageFileToS3(multipartFiles, userId);

            // DB에 프로필 사진을 업데이트 한다.
            memberService.updateUserProfileImage(userId, imageUrl);

            map.put("success", Boolean.TRUE);
        }
        catch(Exception e) {
            e.printStackTrace();
            map.put("success", Boolean.FALSE);
            map.put("error", e.getMessage());
        }

        return map;
    }


}
