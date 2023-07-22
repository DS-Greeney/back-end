package com.greeneyback.member.service;

import com.greeneyback.member.dto.AddrDTO;
import com.greeneyback.member.dto.TourspotDTO;
import com.greeneyback.member.entity.AddrEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.AddrRepository;
import com.greeneyback.member.repository.TourspotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourspotRepository tourspotRepository;
    private final AddrRepository addrRepository;

    public void spotSave(TourspotDTO tourspotDTO) {
        TourspotEntity tourspotEntity = TourspotEntity.toTourspotEntity(tourspotDTO);
        tourspotRepository.save(tourspotEntity);
    }

    public void addrsave(AddrDTO addrDTO) {
        AddrEntity addrEntity = AddrEntity.toAddrEntity(addrDTO);
        addrRepository.save(addrEntity);
    }
}
