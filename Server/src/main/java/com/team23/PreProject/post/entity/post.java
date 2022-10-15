package com.team23.PreProject.post.entity;

//import com.fasterxml.jackson.annotation.JsonBackReference ;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.comment.entity.comment;

import com.team23.PreProject.member_post.entitiy.member_post;
import com.team23.PreProject.post_tag.entity.post_tag;
import com.team23.PreProject.post_vote.entity.post_vote;
import com.team23.PreProject.tag.entity.tag;
import lombok.*;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name="POST")
//json requestbody로 매핑시 json의 키 네임은 소문자로 시작
//간단히 요약하면 클래스의 이름은 일반적으로 대문자로 시작하지만, 개발자들은 식별자가 소문자로 시작하는 것에 익숙하기 때문에 첫 번째 글자를 소문자로 변환한다는 겁니다.
//다만, 모든 문자를 대문자로 사용하는 경우도 있기 때문에 이런 경우는 예외로 둔다고 합니다.
//그리고 예외 케이스를 판별하기 위해 첫 두 문자가 모두 대문자인지를 확인합니다.

public class post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID",nullable = false)
    Integer postId = null;
    //엔티티 필드명은 소문자 시작, camelcase 사용하기
    @Column(name = "POST_NAME")
    String post_name;
    @Column(name = "POST_CONTENT")
    String post_content;
    @Column(name = "VIEW_COUNT")
    Integer view_count = 1;
    @Column(name = "IS_answerED")
    Boolean is_answered = false;
    @Column(name = "SCORE")
    Integer score = 0;
//    @Column(name = "MEMBER_POST_ID")
//    Integer member_post_id;

    @Column(name = "WRITE_DATE")
    ZonedDateTime write_date = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    @Column(name = "MODIFIED_DATE")
    ZonedDateTime modified_date = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));;

//    @ManyToOne(fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name = "MEMBER_ID")
//    //@JsonBackReference 
//    member member;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonIgnore
    List<member_post> member_posts = new ArrayList<>();


    public void addMember_Post(member_post member_post) {
        this.member_posts.add(member_post);

    }

    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST)
    @JsonIgnore
    List<post_vote> post_votes = new ArrayList<>();

    public void addPost_Vote(post_vote post_vote){
        this.post_votes.add(post_vote);
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<comment> comments = new ArrayList<>();

    public void addComments(comment comment)
    {
        this.comments.add(comment);
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<answer> answers = new ArrayList<>();

    public void addAnswers(answer answer)
    {
        this.answers.add(answer);
    }

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    List<post_tag> post_tags = new ArrayList<>();

    public void addPost_tag(post_tag tag)
    {
        this.post_tags.add(tag);
    }
    public post(String name,String content){
        this.post_name = name;
        this.post_content = content;

    }





}
