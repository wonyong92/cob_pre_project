package com.team23.PreProject.post.dto;

import com.team23.PreProject.tag.entity.tag;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class post_info {

    Integer postId;
    String post_name;
    String post_content;
    Integer view_count;
    boolean is_answered;
    Integer score;
    List<String> tags = new ArrayList<>();
    member_info writer;
    ZonedDateTime writeDate;
    ZonedDateTime modifiedDate;

    List<answer_info> answers =new ArrayList<>();
    List<comment_info> comments = new ArrayList<>();
    Integer answerCount = 0;

    public void addTag(String name)
    {
        this.tags.add(name);
    }
}
