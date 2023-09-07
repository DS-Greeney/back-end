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
@Table(name = "spot_comment_table")
public class SpotCommentEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_cmnt_id")
    private int spotCmntId; // 댓글 아이디

    // 관광지 : 1 , 식당 : 2 , 숙소 : 3, 여행코스 : 4
    @Column
    @NotNull
    private int categoryNumber; // 좋아요 누른 카테고리 코드

    @Column
    @NotNull
    private int spotId; // 장소 ID

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = MemberEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    MemberEntity user; // 댓글 작성자

    @Column(length = 300)
    private String spotCmntContent; // 내용

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column
    private LocalDate spotCmntTime; // 작성시간

    @Column(length = 300)
    private int spotCmntStar; // 평점
}
