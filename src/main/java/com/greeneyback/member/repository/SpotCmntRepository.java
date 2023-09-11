package com.greeneyback.member.repository;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotCmntRepository extends JpaRepository<SpotCommentEntity, Integer> {

    List<SpotCommentEntity> findByUser(MemberEntity user);

}
