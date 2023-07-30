package com.greeneyback.member.entity;

import com.greeneyback.member.dto.RstrntDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "restaurant_table")
public class RstrntEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false, length = 50)
    private String rstrntId; // 식당 아이디
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
    //    @Column
    //    private int rstrntStar; // 평균 평점
    //    @Column(length = 500)
    //    private String rstrntImage; // 사진
    //    @Column
    //    private int addrId; //주소아이디(FK)

    public RstrntEntity() {

    }
    public RstrntEntity(String rstrntId, String rstrntName, String rstrntAddr, String rstrntTel, String rstrntMenuinfo, String rstrntLa, String rstrntLo) {
        this.rstrntId = rstrntId;
        this.rstrntName = rstrntName;
        this.rstrntAddr = rstrntAddr;
        this.rstrntTel = rstrntTel;
        this.rstrntMenuinfo = rstrntMenuinfo;
        this.rstrntLa = rstrntLa;
        this.rstrntLo = rstrntLo;
    }

    public static RstrntEntity toRstrntEntity(RstrntDTO rstrntDTO) {
        RstrntEntity rstrntEntity = new RstrntEntity();
        rstrntEntity.setRstrntId(rstrntDTO.getRstrntId());
        rstrntEntity.setRstrntName(rstrntDTO.getRstrntName());
        rstrntEntity.setRstrntAddr(rstrntDTO.getRstrntAddr());
        rstrntEntity.setRstrntTel(rstrntDTO.getRstrntTel());
        rstrntEntity.setRstrntMenuinfo(rstrntDTO.getRstrntMenuinfo());
        rstrntEntity.setRstrntLa(rstrntDTO.getRstrntLa());
        rstrntEntity.setRstrntLo(rstrntDTO.getRstrntLo());

        return rstrntEntity;
    }
}
