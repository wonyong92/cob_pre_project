package com.team23.PreProject.post.dto;

import com.team23.PreProject.tag.entity.tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class post_insert_dto {
    String post_name;
    String post_content;
    Integer member_id;
    List<String> tags=new ArrayList<>();
}
