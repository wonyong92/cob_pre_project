package com.team23.PreProject.post_vote.entity;

//import com.fasterxml.jackson.annotation.JsonBackReference ;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.post.entity.post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "postVote")
public class post_vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer post_vote_id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    member member;
    @ManyToOne
    ////@JsonBackReference ("c")
    @JoinColumn(name="POST_ID")
    post post;
    @Column(name="SCORE")
    Integer score=0;
}
