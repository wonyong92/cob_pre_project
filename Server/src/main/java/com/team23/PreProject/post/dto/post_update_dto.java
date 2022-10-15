package com.team23.PreProject.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class post_update_dto {
    String post_content;
    String post_name;
    List<String> tags=new ArrayList<>();
}
