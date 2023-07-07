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

    @Column(length = 45)
    @NotNull
    private String userPassword; // 비밀번호


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserNickname(memberDTO.getUserNickname());
        memberEntity.setUserEmail(memberDTO.getUserEmail());
        memberEntity.setUserPassword(memberDTO.getUserPassword());
        return memberEntity;
    }
}
