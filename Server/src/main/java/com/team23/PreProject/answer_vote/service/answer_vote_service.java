package com.team23.PreProject.answer_vote.service;

import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.answer_vote.entity.answer_vote;
import com.team23.PreProject.answer_vote.repository.answer_vote_repository;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post_vote.entity.post_vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class answer_vote_service {
    @Autowired
    answer_repository answer_repository;
    @Autowired
    com.team23.PreProject.member.repository.member_repository member_repository;
    @Autowired
    answer_vote_repository answer_vote_repository;

    public String vote(Integer vote, Integer member_id, Integer answer_id) {
        //조건에 맞는 post_vote가 이미 있는지 확인
        try{
            answer_vote answer_vote = answer_vote_repository.findByMemberMemberIdAndAnswerAnswerId(member_id,answer_id).orElseThrow();
            answer answer = answer_repository.findById(answer_id).get();

            member member = member_repository.findById(member_id).get();

            if(answer == null || member ==null || !member.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            {
                return "vote error";
            }

            Integer score = answer_vote.getScore();//이전에 평가한 점수 상태 확인
            if(score >0)
            {
                if(vote>0)
                {
                    return "vote nothing changed";
                }
                else if(vote <0)
                {
                    answer_vote.setScore(0);
                    answer_vote_repository.flush();
                    answer.setScore(answer.getScore()-1);//기존 점수 삭제
                    answer_repository.flush();
                    return "subtract score and current vote score = 0";
                }
            }
            else if(score <0)
            {
                if(vote>0)
                {
                    answer_vote.setScore(0);
                    answer_vote_repository.flush();
                    answer.setScore(answer.getScore()+1);//기존 점수 삭제
                    answer_repository.flush();
                    return "subtract score and current vote score = 0";
                }
                else if(vote <0)
                {
                    return "vote nothing changed";
                }

            }
            else if(score ==0)
            {
                if(vote>0)
                {
                    answer_vote.setScore(0+1);
                    answer_vote_repository.flush();
                    answer.setScore(answer.getScore()+1);//기존 점수 삭제
                    answer_repository.flush();
                    return "add score and current vote score = +1";
                }
                else if(vote <0)
                {
                    answer_vote.setScore(0-1);
                    answer_vote_repository.flush();
                    answer.setScore(answer.getScore()-1);//기존 점수 삭제
                    answer_repository.flush();
                    return "subtract score and current vote score = -1";
                }

            }
        }catch(Exception e){
            //post_vote 가 존재하지 않는다면 생성 및 바로 투표 적용
            answer answer = answer_repository.findById(answer_id).get();
            answer_vote answer_vote = new answer_vote();
            answer_vote.setAnswer(answer);
            answer_vote.setMember(member_repository.findById(member_id).get());

            if(vote>0)
            {
                answer_vote.setScore(+1);
                answer.setScore(answer.getScore()+1);
                answer_repository.flush();
                answer_vote_repository.save(answer_vote);
                return "create answer_vote and add score and current vote score = +1";
            }
            else if(vote <0)
            {
                answer_vote.setScore(-1);
                answer.setScore(answer.getScore()-1);
                answer_repository.flush();
                answer_vote_repository.save(answer_vote);
                return "create answer_vote and add score and current vote score = -1";
            }


        }


        return "vote error";
    }//vote
}
