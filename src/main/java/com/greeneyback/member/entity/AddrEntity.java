package com.greeneyback.member.entity;

import com.greeneyback.member.dto.AddrDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "addr_table")
public class AddrEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false)
    private String addrId;
    @Column
    @NotNull
    private int areaCode;
    @Column
    @NotNull
    private String areaName;
    @Column
    @NotNull
    private int sigunguCode;
    @Column
    @NotNull
    private String sigunguName;

    public static AddrEntity toAddrEntity(AddrDTO addrDTO) {
        AddrEntity addrEntity = new AddrEntity();
        addrEntity.setAddrId(addrDTO.getAddrId());
        addrEntity.setAreaCode(addrDTO.getAreaCode());
        addrEntity.setAreaName(addrDTO.getAreaName());
        addrEntity.setSigunguCode(addrDTO.getSigunguCode());
        addrEntity.setSigunguName(addrDTO.getSigunguName());

        return addrEntity;
    }
}
