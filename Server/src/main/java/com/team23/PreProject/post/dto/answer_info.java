package com.team23.PreProject.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class answer_info {
    Integer answerId;
    String answer_content;
    ZonedDateTime writeDate;
    ZonedDateTime modifiedDate;
    Integer score = 0;
    List<comment_info> answer_comments = new ArrayList<>();
    member_info member;

}
