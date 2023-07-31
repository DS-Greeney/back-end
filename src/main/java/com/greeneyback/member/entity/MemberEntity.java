package com.greeneyback.member.entity;

import com.greeneyback.member.dto.MemberDTO;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;

@Entity
@Setter
@Getter
@DynamicInsert
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

    @Column(columnDefinition = "varchar(20) default '에코그린세포'")
    private String userTitle; // 칭호

    @Column(columnDefinition = "int default 0")
    private Integer challengeNum; // 달성 도전과제 수

    @Column(columnDefinition = "varchar(500) default 'https://firebasestorage.googleapis.com/v0/b/greeney-a996b.appspot.com/o/profile.png?alt=media&token=943e4fe4-50b1-4c02-883d-8925d136fcbe'")
    private String userPicture; // 프로필 사진


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserNickname(memberDTO.getUserNickname());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPassword(memberDTO.getUserPassword());
        memberEntity.setUserPhonenum(memberDTO.getUserPhonenum());
        memberEntity.setUserBirthdate(memberDTO.getUserBirthdate());
        memberEntity.setUserGender(memberDTO.getUserGender());
        memberEntity.setUserTitle(memberDTO.getUserTitle());
        memberEntity.setChallengeNum(memberDTO.getChallengeNum());
        memberEntity.setUserPicture(memberDTO.getUserPicture());
        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setUserNickname(memberDTO.getUserNickname());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPassword(memberDTO.getUserPassword());
        memberEntity.setUserPhonenum(memberDTO.getUserPhonenum());
        memberEntity.setUserBirthdate(memberDTO.getUserBirthdate());
        memberEntity.setUserGender(memberDTO.getUserGender());
        memberEntity.setUserTitle(memberDTO.getUserTitle());
        memberEntity.setChallengeNum(memberDTO.getChallengeNum());
        memberEntity.setUserPicture(memberDTO.getUserPicture());
        return memberEntity;
    }
}
