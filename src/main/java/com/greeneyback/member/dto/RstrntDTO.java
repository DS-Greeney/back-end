package com.greeneyback.member.dto;

import com.greeneyback.member.entity.RstrntEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RstrntDTO {

    private String rstrntId;
    private String rstrntName;
    private String rstrntAddr;
    private String rstrntTel;
    private String rstrntMenuinfo;
    private String rstrntLa;
    private String rstrntLo;
//    private int rstrntStar;
//    private String rstrntImage;
//    private int addrId;

    public static RstrntDTO toRstrntDTO(RstrntEntity rstrntEntity) {
        RstrntDTO rstrntDTO = new RstrntDTO();
        rstrntDTO.setRstrntId(rstrntEntity.getRstrntId());
        rstrntDTO.setRstrntName(rstrntEntity.getRstrntName());
        rstrntDTO.setRstrntAddr(rstrntEntity.getRstrntAddr());
        rstrntDTO.setRstrntTel(rstrntEntity.getRstrntTel());
        rstrntDTO.setRstrntMenuinfo(rstrntEntity.getRstrntMenuinfo());
        rstrntDTO.setRstrntLa(rstrntEntity.getRstrntLa());
        rstrntDTO.setRstrntLo(rstrntEntity.getRstrntLo());

        return rstrntDTO;
    }
}
