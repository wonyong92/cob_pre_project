package com.team23.PreProject.tag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post_tag.entity.post_tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TAG")
@Getter
@Setter
public class tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    Integer id;
    @Column(name = "NAME")
    String name;
    @Column(name = "CONTENT")
    String content;
    @Column(name = "QUESTIONS")
    Integer stub_questions = 1000;


    @OneToMany(mappedBy = "tag")
    List<post_tag> post_tags = new ArrayList<>();

    public void addPost_tag(post_tag tag)
    {
        this.post_tags.add(tag);
    }

}
