package com.greeneyback.member.service;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.repository.SpotCmntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotCmntService {

    @Autowired
    SpotCmntRepository spotCmntRepository;

    public List<SpotCommentEntity> findByUser(MemberEntity user) {
        return spotCmntRepository.findByUser(user);
    }
}
