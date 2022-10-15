package com.team23.PreProject.post.dto;

import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.profile.entity.profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class post_profile_dto {
    com.team23.PreProject.post.entity.post post;
    profile profile;
    String tag="java";

}
