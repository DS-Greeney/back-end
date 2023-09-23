package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// 검색기능 - 모든 장소 데이터(관광지, 식당, 숙소)를 간단히 담은 Entity
@Entity
@Setter
@Getter
@Table(name = "all_spot_table")
public class AllSpotEntity {

    @Id // pk 지정
    @Column(unique = true, nullable = false, length = 50, name = "data_code")
    private Integer dataCode; // 기본키로 달아준다.

    @Column
    @NotNull
    private Integer spotId;  // 그 장소의 고유 ID

    @Column
    @NotNull
    private Integer categoryNumber;  // 전체 : 0, 관광지 : 1, 식당 : 2, 숙소 : 3

    @Column(length = 100)
    private String address; // 주소

    @Column
    @NotNull
    private Integer areaCode;

    @Column(precision = 13, scale = 10)
    @NotNull
    private BigDecimal latitude;

    @Column(precision = 13, scale = 10)
    @NotNull
    private BigDecimal longitude;

    @Column(length = 100)
    private String spotName;  // 장소 이름

    @Column(columnDefinition = "TEXT")
    private String spotInfo; // 장소 설명 (식당인 경우 rst_ctrgy)

    @Column(length = 200)
    private String spotImage;  // 관광지만 들어감.


}
