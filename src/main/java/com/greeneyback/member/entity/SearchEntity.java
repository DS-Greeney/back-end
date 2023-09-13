package com.greeneyback.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "search_table")
public class SearchEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_cmnt_id")
    private int searchId; // 검색 아이디

    // 전체 : 0, 관광지 : 1 , 식당 : 2 , 숙소 : 3, 여행코스 : 4
    @Column
    private int category; // 검색 카테고리 => 검색한 위치에 따라 달라짐

    @NotNull
    @Column(length = 300)
    private String searchContent; // 검색 내용

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column
    private LocalDate searchTime; // 검색시간

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = MemberEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    MemberEntity user; // 검색한 사용자

}
