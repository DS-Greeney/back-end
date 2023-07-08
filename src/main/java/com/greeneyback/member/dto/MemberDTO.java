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

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setUserNickname(memberEntity.getUserNickname());
        memberDTO.setUserEmail(memberEntity.getUserEmail());
        memberDTO.setUserPassword(memberEntity.getUserPassword());
        return memberDTO;
    }
}
