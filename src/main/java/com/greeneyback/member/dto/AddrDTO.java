package com.greeneyback.member.dto;
import com.greeneyback.member.entity.AddrEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddrDTO {

    private String addrId;
    private int areaCode;
    private String areaName;
    private int sigunguCode;
    private String sigunguName;

    public static AddrDTO toAddrDTO(AddrEntity addrEntity) {
        AddrDTO addrDTO = new AddrDTO();
        addrDTO.setAddrId(addrEntity.getAddrId());
        addrDTO.setAreaName(addrEntity.getAreaName());
        addrDTO.setAreaCode(addrEntity.getAreaCode());
        addrDTO.setSigunguCode(addrEntity.getSigunguCode());
        addrDTO.setSigunguName(addrEntity.getSigunguName());

        return addrDTO;
    }
}
