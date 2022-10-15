package com.team23.PreProject.answer_vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.member.entity.member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ANSWER_VOTE")
public class answer_vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ANSWER_VOTE_ID")
    private Integer answerVoteId;

    @Column(name="")

    private Integer score=0;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")

    private com.team23.PreProject.answer.entity.answer answer;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")

    private com.team23.PreProject.member.entity.member member;
}
