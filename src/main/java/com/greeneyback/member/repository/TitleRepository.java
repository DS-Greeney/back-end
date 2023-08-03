package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<TitleEntity, Integer> {
}
