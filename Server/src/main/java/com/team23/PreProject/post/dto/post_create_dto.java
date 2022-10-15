package com.team23.PreProject.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter

public class post_create_dto {
    Integer postId;


    String post_name;

    String post_content;

    ZonedDateTime write_date;

    ZonedDateTime modified_date;


}
