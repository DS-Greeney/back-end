package com.greeneyback.member.entity;

import com.greeneyback.member.dto.RstrntDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "restaurant_table")
public class RstrntEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false, length = 50, name = "rstrnt_id")
    private int rstrntId; // 식당 아이디
    @Column(length = 200)
    private String rstrntCtgry;  // 식당 카테고리
    @Column(length = 200)
    private String rstrntName; // 식당명
    @Column(length = 500)
    private String rstrntAddr; // 주소
    @Column(length = 20)
    private String rstrntTel; // 전화번호
    @Column(length = 500)
    private String rstrntMenuinfo; // 메뉴설명
    @Column(length = 20)
    private String rstrntLa; // 위도
    @Column(length = 20)
    private String rstrntLo; // 경도
    @Column
    private float rstrntStar; // 평균 평점
    @Column
    private Integer areaCode;

    public RstrntEntity() {

    }
    public RstrntEntity(int rstrntId, String rstrntCtgry, String rstrntName, String rstrntAddr, String rstrntTel, String rstrntMenuinfo, String rstrntLa, String rstrntLo, float rstrntStar, Integer areaCode) {
        this.rstrntId = rstrntId;
        this.rstrntCtgry = rstrntCtgry;
        this.rstrntName = rstrntName;
        this.rstrntAddr = rstrntAddr;
        this.rstrntTel = rstrntTel;
        this.rstrntMenuinfo = rstrntMenuinfo;
        this.rstrntLa = rstrntLa;
        this.rstrntLo = rstrntLo;
        this.rstrntStar = rstrntStar;
        this.areaCode = areaCode;
    }

    public static RstrntEntity toRstrntEntity(RstrntDTO rstrntDTO) {
        RstrntEntity rstrntEntity = new RstrntEntity();
        rstrntEntity.setRstrntId(rstrntDTO.getRstrntId());
        rstrntEntity.setRstrntCtgry(rstrntDTO.getRstrntCtgry());
        rstrntEntity.setRstrntName(rstrntDTO.getRstrntName());
        rstrntEntity.setRstrntAddr(rstrntDTO.getRstrntAddr());
        rstrntEntity.setRstrntTel(rstrntDTO.getRstrntTel());
        rstrntEntity.setRstrntMenuinfo(rstrntDTO.getRstrntMenuinfo());
        rstrntEntity.setRstrntLa(rstrntDTO.getRstrntLa());
        rstrntEntity.setRstrntLo(rstrntDTO.getRstrntLo());
        rstrntEntity.setRstrntStar(rstrntDTO.getRstrntStar());
        rstrntEntity.setAreaCode(rstrntDTO.getAreaCode());

        return rstrntEntity;
    }
}
