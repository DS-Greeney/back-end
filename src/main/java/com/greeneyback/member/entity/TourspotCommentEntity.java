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
@Table(name = "tourspot_comment_table")
public class TourspotCommentEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourspot_cmnt_id")
    private int tourspotCmntId; // 댓글 아이디

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = TourspotEntity.class)
    @JoinColumn(referencedColumnName = "tourspot_id", name = "tourspot_id")
    TourspotEntity tourspot; // 게시글(관광지) 아이디

    @ManyToOne(cascade = CascadeType.REMOVE, targetEntity = MemberEntity.class)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    MemberEntity user; // 댓글 작성자

    @Column(length = 300)
    private String tourspotCmntContent; // 내용

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column
    private LocalDate tourspotCmntTime; // 작성시간

    @Column(length = 300)
    private int tourspotCmntStar; // 평점
}
