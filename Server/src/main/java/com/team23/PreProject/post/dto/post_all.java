package com.team23.PreProject.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import  org.springframework.data.domain.Sort;

@Getter
@Setter
public class post_all {
    List<post_info> posts = new ArrayList<>();
    long TotalPages;
    long questions;
    long PageNumber;

    String SortBy;
    Integer size;

    public void addPost_info(post_info info)
    {
        this.posts.add(info);
    }



}
