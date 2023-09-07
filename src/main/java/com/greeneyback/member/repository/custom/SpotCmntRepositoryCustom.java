package com.greeneyback.member.repository.custom;

import com.greeneyback.member.entity.SpotCommentEntity;

import java.util.List;

public interface SpotCmntRepositoryCustom {

    List<SpotCommentEntity> findBySpotIdAndCategoryNumber(int spotId, int categoryNumber);
}
