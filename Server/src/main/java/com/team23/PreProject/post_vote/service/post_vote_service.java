package com.team23.PreProject.post_vote.service;

import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.repository.post_repository;
import com.team23.PreProject.post.service.post_service;
import com.team23.PreProject.post_vote.entity.post_vote;
import com.team23.PreProject.post_vote.repository.post_vote_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class post_vote_service {
    @Autowired
    com.team23.PreProject.post.repository.post_repository post_repository;
    @Autowired
    com.team23.PreProject.member.repository.member_repository member_repository;
    @Autowired
    com.team23.PreProject.post_vote.repository.post_vote_repository post_vote_repository;

    public String vote(Integer vote, Integer member_id, Integer post_id) {
        //조건에 맞는 post_vote가 이미 있는지 확인
        try{
            post_vote post_vote = post_vote_repository.findByMemberMemberIdAndPostPostId(member_id,post_id).orElseThrow();
            post post = post_repository.findById(post_id).get();
            member member = member_repository.findById(member_id).get();

            if(post == null || member ==null || !member.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            {
                return "vote error";
            }

            Integer score = post_vote.getScore();//이전에 평가한 점수 상태 확인
            if(score >0)
            {
                if(vote>0)
                {
                    return "vote nothing changed";
                }
                else if(vote <0)
                {
                    post_vote.setScore(0);
                    post_vote_repository.flush();
                    post.setScore(post.getScore()-1);//기존 점수 삭제
                    post_repository.flush();
                    return "subtract score and current vote score = 0";
                }
            }
            else if(score <0)
            {
                if(vote>0)
                {
                    post_vote.setScore(0);
                    post_vote_repository.flush();
                    post.setScore(post.getScore()+1);//기존 점수 삭제
                    post_repository.flush();
                    return "add score and current vote score = 0";
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
                    post_vote.setScore(+1);
                    post_vote_repository.flush();
                    post.setScore(post.getScore()+1);//기존 점수 +1
                    post_repository.flush();
                    return "add score and current vote score = 1";
                }
                else if(vote <0)
                {
                    post_vote.setScore(-1);
                    post_vote_repository.save(post_vote);
                    post.setScore(post.getScore()-1);//기존 점수 삭제
                    post_repository.flush();
                    return "subtract score and current vote score = -1";
                }

            }
        }catch(Exception e){
            //post_vote 가 존재하지 않는다면 생성 및 바로 투표 적용
            post post = post_repository.findById(post_id).get();
            post_vote post_vote = new post_vote();
            post_vote.setPost(post);
            post_vote.setMember(member_repository.findById(member_id).get());

            if(vote>0)
            {
                post_vote.setScore(+1);
                post.setScore(post.getScore()+1);
                post_repository.flush();
                post_vote_repository.save(post_vote);
                return "create post_vote and add score and current vote score = +1";
            }
            else if(vote <0)
            {
                post_vote.setScore(-1);
                post.setScore(post.getScore()-1);
                post_repository.flush();
                post_vote_repository.save(post_vote);
                return "create post_vote and subtract score and current vote score = -1";
            }


        }


        return "vote error";
    }//vote
}
