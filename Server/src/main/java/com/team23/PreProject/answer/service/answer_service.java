package com.team23.PreProject.answer.service;

import com.team23.PreProject.answer.dto.*;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.comment.entity.comment;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.post.dto.member_info;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.repository.post_repository;
import com.team23.PreProject.profile.entity.profile;
import com.team23.PreProject.profile.repository.profile_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class answer_service {

    private final answer_repository answer_repository;
    private final post_repository post_repository;
    private final member_repository member_repository;
    @Autowired
    profile_repository profile_repository;

    public boolean select(Integer post_id, Integer answer_id) {
        try {
            boolean result;
            post post = post_repository.findById(post_id).orElseThrow();
            post.setIs_answered(true);
            List<answer> answers = post.getAnswers();
            answer sel = new answer();
            int old = -1;
            for(answer temp : answers)
            {
                if(temp.getAccepted())
                {
                    temp.setAccepted(false);
                    old = temp.getAnswerId();
                    answer_repository.flush();
                }
            }
            for(answer temp : answers)
            {

                if(temp.getAnswerId() == answer_id)
                {
                    sel = temp;
                    break;
                }
            }
            if(sel.getAnswerId()==null)
            {
                throw new Exception();
            }

            sel.setAccepted(true);
            if(old == answer_id)
            {
                sel.setAccepted(false);
                post.setIs_answered(false);
                post_repository.flush();
            }
            answer_repository.flush();

            return true;
        }catch(Exception e)
        {
            return false;
        }





    }


    public answer createAnswer(answer_dto.Post RequestBody){
        post post = post_repository.findById(RequestBody.getPostId()).orElseThrow();

        profile profile = profile_repository.findById(RequestBody.getMemberId()).get();

        member member = member_repository.findById(RequestBody.getMemberId()).orElseThrow();

        answer answer = new answer();

        answer.setMember(member);
        answer.setPost(post);
        answer.setAnswer_content(RequestBody.getContent());


        answer_repository.save(answer);
        System.out.println("===================save answerend \n\n");


        member.addAnswer(answer);
        member_repository.flush();
        profile.setAnswers(profile.getAnswers()+1);

        return answer;
    }

    public Page<answer> findAnswers(Integer questionId, int page,int size) {
        post findPost = post_repository.findById(questionId).orElseThrow();
        return answer_repository.findAllByPost(findPost, PageRequest.of(page, size,
                Sort.by("score").descending()));
    }

    public answer updateAnswer(Integer answerId, String content) {
        answer answer = answer_repository.findById(answerId).orElseThrow();
        answer.setAnswer_content(content);
        return answer;
    }

    public String deleteAnswer(Integer answerId){
        try {
            profile profile = profile_repository.findById(answer_repository.findById(answerId).get().getMember().getMemberId()).get();
            profile.setAnswers(profile.getAnswers()-1);
            profile_repository.flush();
            System.out.println("\nprofile chageing");
            answer answer = answer_repository.findById(answerId).get();
            System.out.println("\nget answer "+answer.getAnswerId());
            if(answer!=null )
            {
                if(answer.getAccepted())
                {
                    System.out.println("\npost chageing start ");
                    post post = post_repository.findById(answer.getPost().getPostId()).get();
                    System.out.println("\npost "+post.getPostId());
                    post.setIs_answered(false);
                    System.out.println("\npost set not acc");
                    System.out.println("\npost chageing");
                    post_repository.flush();
                }
                answer_repository.deleteById(answerId);

                return "true";
            }
            else
                return "false";

        }
        catch(Exception e)
        {
            return "false";
        }
    }

    public answer_dto.ByMemberDto findAnswersBymember(Integer memberId, int page, int size) {
        member member = member_repository.findById(memberId).get();
        List<answer> member_answer = member.getAnswers();
        List<answer> answers = new ArrayList<>();
        answer ans;
        for(int i = page*size;i<page*size+size;i++)
        {
            if(i>=member_answer.size())
            {
                break;
            }
            ans = member_answer.get(i);
            ans.setPostId(ans.getPost().getPostId());
            answers.add(ans);
        }//및;ㅣㄴㅏ pagenation 적용해야지;;;;
        PageInfo pageInfo = new PageInfo(page+1,size,member_answer.size(),member_answer.size()/size+1);


        Collections.sort(answers, new comp());


        List<answer_info> answer_infos = new ArrayList<>();
        answer_info temp;
        for(answer answ : answers)
        {
            temp= new answer_info();
            System.out.println("answers====================");
            temp.setAnswer_id(answ.getAnswerId());
            temp.setAnswer_content(answ.getAnswer_content());
            temp.setAccepted(answ.getAccepted());
            comment_info ctemp = new comment_info();
            List<comment_info> ctemps = new ArrayList<>();
            for(comment com : answ.getComments())
            {
                ctemp.setCommentId(com.getCommentId());
                ctemp.setContent(com.getContent());
            }
            temp.setComments(ctemps);
            temp.setWrite_date(answ.getWriteDate());
            temp.setModified_date(answ.getModifiedDate());
            temp.setScore(answ.getScore());
            member_info mtemp = new member_info();
            mtemp.setMember_id(member.getMemberId());
            mtemp.setNickName(member.getNickName());
            temp.setMember(mtemp);
            temp.setPost_id(answ.getPostId());
            temp.setAccepted(answ.getAccepted());

            answer_infos.add(temp);

        }

        answer_dto.ByMemberDto dto = new answer_dto.ByMemberDto(pageInfo,answer_infos);


        return dto;
    }
}
