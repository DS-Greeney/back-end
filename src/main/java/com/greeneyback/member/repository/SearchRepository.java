package com.greeneyback.member.repository;


import com.greeneyback.member.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
}
