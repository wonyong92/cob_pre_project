package com.team23.PreProject.comment.entity;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.post.entity.post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    Integer commentId;

    @Column(name="COMMENT_content")
    String content;

    @CreationTimestamp
    @Column(name="comment_date")
    LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name="comment_modified_date")
    LocalDateTime modified_date;

    @Column(name="is_updated")
    Boolean is_update =false;



    @ManyToOne
    @JoinColumn(name = "POST_ID")
    post post;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    answer answer;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
     member member;

    public comment(String content, post post, member member) {
        this.content = content;
        this.post = post;
        this.member = member;
    }

    public comment(String content, answer answer, member member) {
        this.content = content;
        this.answer = answer;
        this.member = member;
    }
}