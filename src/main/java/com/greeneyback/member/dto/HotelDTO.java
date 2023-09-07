package com.greeneyback.member.dto;

import com.greeneyback.member.entity.HotelEntity;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelDTO {

    private int hotelId; // 호텔 아이디
    private String hotelName;  // 호텔 이름
    private String hotelAddr; // 주소
    private String hotelTel; // 전화번호
    private String hotelService;  // 편의시설 및 서비스
    private String hotelInfo; // 호텔 설명
    private BigDecimal hotelLa;
    private BigDecimal hotelLo;
    private Integer areaCode; // 지역 코드
    private String hotelUrl;  // 관련 홈페이지
    private float hotelStar;  // 평점

    public static HotelDTO toHotelDTO(HotelEntity hotelEntity) {
        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setHotelId(hotelEntity.getHotelId());
        hotelDTO.setHotelName(hotelEntity.getHotelName());
        hotelDTO.setHotelAddr(hotelEntity.getHotelAddr());
        hotelDTO.setHotelTel(hotelEntity.getHotelTel());
        hotelDTO.setHotelService(hotelEntity.getHotelService());
        hotelDTO.setHotelInfo(hotelEntity.getHotelInfo());
        hotelDTO.setHotelLa(hotelEntity.getHotelLa());
        hotelDTO.setHotelLo(hotelEntity.getHotelLo());
        hotelDTO.setAreaCode(hotelEntity.getAreaCode());
        hotelDTO.setHotelUrl(hotelEntity.getHotelUrl());
        hotelDTO.setHotelStar(hotelEntity.getHotelStar());

        return hotelDTO;
    }

}
