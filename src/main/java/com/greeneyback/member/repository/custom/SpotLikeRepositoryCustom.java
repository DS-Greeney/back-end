package com.greeneyback.member.repository.custom;


public interface SpotLikeRepositoryCustom {
    void deleteSpotLikeByCategoryNumAndSpotIdAndUserId(int categoryNumber, int spotId, Long userId);
}
