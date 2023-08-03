package com.greeneyback.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "tourspot_comment_table")
public class TourspotCommentEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tourspotCmntId; // 댓글 아이디

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "tourspot_id")
    TourspotEntity tourspot; // 게시글(관광지) 아이디

    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    @JoinColumn(referencedColumnName = "user_id")
    MemberEntity user; // 댓글 작성자

    @Column(length = 300)
    private String tourspotCmntContent; // 내용

    @Column(length = 500)
    private String tourspotCmntImg; // 사진

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date tourspotCmntTime; // 작성시간

    @Column(length = 300)
    private int tourspotCmntStar; // 평점
}
