package com.team23.PreProject.comment.repository;

import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.comment.entity.comment;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.post.entity.post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface comment_repository extends JpaRepository<comment, Integer> {
    List<comment> findAllByAnswer(answer answer);
    List<comment> findAllByPost(post post);

    List<comment> findAllByMember(Integer member_id);

    List<comment> findByMember(member member);
}