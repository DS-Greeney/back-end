package com.greeneyback.member.repository;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.custom.RstrntRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RstrntRepository extends JpaRepository<RstrntEntity, String>, RstrntRepositoryCustom {
}
