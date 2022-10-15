package com.team23.PreProject.member.entity;

//import com.fasterxml.jackson.annotation.JsonBackReference ;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.member_post.entitiy.member_post;
import com.team23.PreProject.post_vote.entity.post_vote;
import com.team23.PreProject.profile.entity.profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name="MEMBER")
public class member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID",nullable = false)
    Integer memberId;
    @Column(name = "sign_in_date")
    ZonedDateTime signInDate= ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    @Column(name = "PASSWORD")
    String Password;
    @Column(name = "NICKNAME")
    String nickName;
    @Column(name = "ID")
    String id;

//    @Column(name = "COMMENT_ID")
//    String comment_id;
    @Column(name = "ROLE")
    String roles;

    @Column(name = "ProfileImage")
    String profileImage = "default.jpg";

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
//    @Column(name = "answer_ID")
//    String answer_id;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<answer> answers = new ArrayList<>();

    public void addAnswer(answer answer)
    {
        this.answers.add(answer);
    }

    public member(String Password, String nick,String Id,profile pf, String com)
    {
        this.Password = Password;
        this.nickName = nick;
        this.id = Id;
        this.profile=pf;
        //this.comment_id=com;


    }//"Password","nick name","Id",null,null,null)
    public member(String password, String nickName,String Id)
    {
        this.Password = password;
        this.nickName = nickName;
        this.id = Id;
    }

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "member")
//    List<post> Posts = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name="POST_ID")
//    post post;
    //@OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    List<member_post> member_posts = new ArrayList<>();




    public void addMember_Post(member_post member_post)
    {

        this.member_posts.add(member_post);
    }
//    @OneToMany
//    @JoinColumn(name = "comment_id")
//    List<comment_entity> comments = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "answer_id")
//    List<answer_entity> answers = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "PROFILE_ID")
    profile profile;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<post_vote> post_votes = new ArrayList<>();

    public void addPost_vote(post_vote post_vote){
        this.post_votes.add(post_vote);
    }




}
