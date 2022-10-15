package com.team23.PreProject.post.dto;

import com.team23.PreProject.post.entity.post;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class postWithTag {
    post post = new post();
    List<String> tags=new ArrayList<>();

    public void addTag(String name)
    {
        this.tags.add(name);
    }


    public void setPostInfo(com.team23.PreProject.post.entity.post post) {
        this.post.setPostId(post.getPostId());
        this.post.setPost_name(post.getPost_name());
        this.post.setPost_content(post.getPost_content());
        this.post.setWrite_date(post.getWrite_date());
        this.post.setModified_date(post.getModified_date());
        this.post.setView_count(post.getView_count());


    }
}
