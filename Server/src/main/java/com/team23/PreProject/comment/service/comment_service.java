package com.team23.PreProject.comment.service;

import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.comment.dto.comment_dto;
import com.team23.PreProject.comment.entity.comment;
import com.team23.PreProject.comment.repository.comment_repository;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.repository.post_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service

public class comment_service {
    @Autowired
    comment_repository commentRepository;
    @Autowired
    private post_repository postRepository;
    @Autowired
    private answer_repository answerRepository;
    @Autowired
    private member_repository memberRepository;
    public comment createAnswerComment(Integer answerId, Integer memberId, String content){
        answer findAnswer = answerRepository.findById(answerId).orElseThrow();
        member findMember = memberRepository.findById(memberId).orElseThrow();
//사용자 확인 일시 정지
//        if(!findMember.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
//        {
//            return null;
//        }
        comment comment = new comment(content, findAnswer, findMember);
        return commentRepository.save(comment);
    }

    public comment createPostComment(Integer postId, Integer memberId, String content){
        post findPost = postRepository.findById(postId).orElseThrow();
        member findMember = memberRepository.findById(memberId).orElseThrow();
        comment comment = new comment(content, findPost, findMember);

//사용자 확인 일시 정지
//        if(!findMember.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
//        {
//            return null;
//        }
        return commentRepository.save(comment);
    }

    public List<comment> getAnswerComment(Integer answerId){
        answer findAnswer = answerRepository.findById(answerId).orElseThrow();
        return commentRepository.findAllByAnswer(findAnswer);
    }

    public List<comment> getPostComment(Integer questionId){
        post findPost = postRepository.findById(questionId).orElseThrow();
        return commentRepository.findAllByPost(findPost);
    }

    public comment updateComment(Integer commentId, String content,Integer memberId){
        member findMember = memberRepository.findById(memberId).orElseThrow();
        if(!findMember.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            return null;
        }
        comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.setContent(content);
        comment.setIs_update(true);
        return comment;
    }

    public boolean deleteComment(comment_dto.DelRequest dto){
        Integer memberId = dto.getMemberId();

        member findMember = memberRepository.findById(memberId).orElseThrow();
        if(!findMember.getId().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            return false;
        }
        Integer commentId = dto.getCommentId();
        try{
        comment comment = commentRepository.findById(commentId).orElseThrow();
        commentRepository.delete(comment);

        return true;
        }
        catch(Exception e)
        {
            return false;
        }


    }
}
