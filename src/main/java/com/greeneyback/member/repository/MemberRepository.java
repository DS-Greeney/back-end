package com.greeneyback.member.repository;

import com.greeneyback.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from user_table where user_email=?)
    Optional<MemberEntity> findByUserEmail(String userEmail);
}
