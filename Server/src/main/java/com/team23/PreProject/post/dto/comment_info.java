package com.team23.PreProject.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class comment_info {
    Integer commentId;
    String content;
    LocalDateTime createDate;
    LocalDateTime modified_date;
    boolean is_update =false;
    member_info writer;

}
