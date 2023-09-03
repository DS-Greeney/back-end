package com.greeneyback.member.dto;

import com.greeneyback.member.entity.RstrntEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RstrntDTO {

    private String rstrntId;
    private String rstrntCtgry;
    private String rstrntName;
    private String rstrntAddr;
    private String rstrntTel;
    private String rstrntMenuinfo;
    private String rstrntLa;
    private String rstrntLo;
    private float rstrntStar;
    private Integer areaCode;

    public static RstrntDTO toRstrntDTO(RstrntEntity rstrntEntity) {
        RstrntDTO rstrntDTO = new RstrntDTO();
        rstrntDTO.setRstrntId(rstrntEntity.getRstrntId());
        rstrntDTO.setRstrntCtgry(rstrntDTO.getRstrntCtgry());
        rstrntDTO.setRstrntName(rstrntEntity.getRstrntName());
        rstrntDTO.setRstrntAddr(rstrntEntity.getRstrntAddr());
        rstrntDTO.setRstrntTel(rstrntEntity.getRstrntTel());
        rstrntDTO.setRstrntMenuinfo(rstrntEntity.getRstrntMenuinfo());
        rstrntDTO.setRstrntLa(rstrntEntity.getRstrntLa());
        rstrntDTO.setRstrntLo(rstrntEntity.getRstrntLo());
        rstrntDTO.setRstrntStar(rstrntEntity.getRstrntStar());
        rstrntDTO.setAreaCode(rstrntEntity.getAreaCode());

        return rstrntDTO;
    }
}
