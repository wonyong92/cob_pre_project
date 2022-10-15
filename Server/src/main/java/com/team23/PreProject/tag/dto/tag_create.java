package com.team23.PreProject.tag.dto;

import com.team23.PreProject.answer.dto.answer_dto;
import com.team23.PreProject.post.entity.post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class tag_create {
    String name;
    String content;
    post post ;
}
