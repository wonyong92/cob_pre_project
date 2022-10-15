package com.team23.PreProject.answer.dto;

import com.team23.PreProject.post.dto.member_info;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter

public class answer_info {
   Integer answer_id;
   String answer_content;
   ZonedDateTime write_date;
   ZonedDateTime modified_date;
   Integer score;
   List<comment_info> comments;
   member_info member;
   Integer post_id;
   boolean accepted;
}
