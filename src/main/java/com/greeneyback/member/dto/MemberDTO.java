package com.greeneyback.member.dto;

import com.greeneyback.member.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userPassword;
    private String userPhonenum;
    private String userBirthdate;
    private Integer userGender;
    private String userTitle;
    private Integer challengeNum;
    private Integer userIsSuper;
    private String userPicture;

    // entity -> dto로 변환 메서드
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setUserNickname(memberEntity.getUserNickname());
        memberDTO.setUserEmail(memberEntity.getUserEmail());
        memberDTO.setUserPassword(memberEntity.getUserPassword());
        return memberDTO;
    }
}
