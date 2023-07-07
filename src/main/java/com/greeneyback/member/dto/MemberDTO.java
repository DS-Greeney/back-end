package com.greeneyback.member.dto;

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
}
