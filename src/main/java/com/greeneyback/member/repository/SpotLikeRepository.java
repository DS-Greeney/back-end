package com.greeneyback.member.repository;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpotLikeRepository extends JpaRepository<SpotLikeEntity, Integer> {

    List<SpotLikeEntity> findByUser(MemberEntity user);
}
