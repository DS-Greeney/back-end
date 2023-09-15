package com.greeneyback.member.repository;

import com.greeneyback.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 인터페이스이기 때문에 추상메서드 추가 가능
    // 이메일로 회원 정보 조회 (select * from user_table where user_email=?)
    Optional<MemberEntity> findByUserEmail(String userEmail);  // Optional로 객체가 null일 때 방지, 주고받는 것은 enetity 객체
    MemberEntity findByUserId(Long userId);

    Optional<MemberEntity> findByUserNickname(String userNickname);
}
