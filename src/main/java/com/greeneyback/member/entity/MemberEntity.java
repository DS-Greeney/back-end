package com.greeneyback.member.entity;

import com.greeneyback.member.dto.MemberDTO;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long userId;

    @Column(unique = true, nullable = false, length = 20)
    private String userNickname; // 닉네임

    @Column(unique = true, nullable = false, length = 45)
    private String userEmail; // 이메일

    @Column(nullable = false, length = 45)
    private String userPassword; // 비밀번호

    @Column(unique = true, nullable = false, length = 20)
    private String userPhonenum; // 휴대폰 번호

    @Column(nullable = false, length = 20)
    private String userBirthdate; // 생년월일

    @Column(nullable = false)
    private Integer userGender; // 성별

    @Column(length = 20)
    private String userTitle; // 칭호

    @Column
    private Integer challengeNum; // 달성 도전과제 수

    @Column
    private String userPicture; // 프로필 사진



    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserNickname(memberDTO.getUserNickname());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPassword(memberDTO.getUserPassword());
        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setUserNickname(memberDTO.getUserNickname());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPassword(memberDTO.getUserPassword());
        return memberEntity;
    }
}
