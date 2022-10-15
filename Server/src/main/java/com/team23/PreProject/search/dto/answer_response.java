package com.team23.PreProject.search.dto;

import com.team23.PreProject.post.dto.post_info;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class answer_response {
    List<post_info> posts;
    Integer questions;
}
