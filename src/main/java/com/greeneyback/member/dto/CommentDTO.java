package com.greeneyback.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.TourspotEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {

    private int tourspotCmntId; // 댓글 아이디
    private int spotId; // 게시글 아이디
    private Long userId; // 댓글 작성자
    private String cmntContent; // 내용
    private String cmntImg; // 사진
    private Date cmntTime; // 작성시간
    private int cmntStar; // 평점
}
