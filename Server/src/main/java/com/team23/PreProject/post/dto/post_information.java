package com.team23.PreProject.post.dto;

import com.team23.PreProject.tag.entity.tag;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class post_information{
    int score;
    int answersCount;
    String post_name;
    boolean isQuestion;
    String partOfPost;
    int views;
    int member_id;
    int stub_user_reputation;
    Date write_date;
    ArrayList<tag> tagList;


}
