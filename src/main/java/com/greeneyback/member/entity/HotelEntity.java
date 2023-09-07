package com.greeneyback.member.entity;

import com.greeneyback.member.dto.HotelDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "hotel_table")
public class HotelEntity {
    @Id // pk 지정
    @Column(unique = true, nullable = false, length = 50, name = "hotel_id")
    private Integer hotelId; // 호텔 아이디
    @Column(length = 100)
    private String hotelName;  // 호텔 이름
    @Column(length = 100)
    private String hotelAddr; // 주소
    @Column(length = 20)
    private String hotelTel; // 전화번호
    @Column(length = 200)
    private String hotelService;  // 편의시설 및 서비스
    @Column(columnDefinition = "TEXT")
    private String hotelInfo; // 호텔 설명
    @Column(precision = 13, scale = 10)
    @NotNull
    private BigDecimal hotelLa;
    @Column(precision = 13, scale = 10)
    @NotNull
    private BigDecimal hotelLo;
    @Column
    private Integer areaCode; // 지역 코드
    @Column(length = 200)
    private String hotelUrl;  // 관련 홈페이지
    @Column(columnDefinition = "float default 0")
    private float hotelStar;


    public static HotelEntity toHotelEntity(HotelDTO hotelDTO) {
        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.setHotelId(hotelDTO.getHotelId());
        hotelEntity.setHotelName(hotelDTO.getHotelName());
        hotelEntity.setHotelAddr(hotelDTO.getHotelAddr());
        hotelEntity.setHotelTel(hotelDTO.getHotelTel());
        hotelEntity.setHotelService(hotelDTO.getHotelService());
        hotelEntity.setHotelInfo(hotelDTO.getHotelInfo());
        hotelEntity.setHotelLa(hotelDTO.getHotelLa());
        hotelEntity.setHotelLo(hotelDTO.getHotelLo());
        hotelEntity.setAreaCode(hotelDTO.getAreaCode());
        hotelEntity.setHotelUrl(hotelDTO.getHotelUrl());
        hotelEntity.setHotelStar(hotelDTO.getHotelStar());

        return hotelEntity;
    }

}
