package com.greeneyback.member.repository;

import com.greeneyback.member.entity.TourspotEntity;

public interface TourspotRepositoryCustom {
    TourspotEntity findBySigunguCode(Integer code);
}
