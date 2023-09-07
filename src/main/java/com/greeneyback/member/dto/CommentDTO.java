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

    private int spotCmntId; // 댓글 아이디
    private int spotId; // 게시글 아이디
    private int categoryNumber; // 카테고리 넘버 // 관광지 : 1 , 식당 : 2 , 숙소 : 3, 여행코스 : 4
    private Long userId; // 댓글 작성자
    private String cmntContent; // 내용
    private String cmntImg; // 사진
    private Date cmntTime; // 작성시간
    private int cmntStar; // 평점
}
