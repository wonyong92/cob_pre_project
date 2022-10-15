package com.team23.PreProject.answer_vote.repository;

import com.team23.PreProject.answer_vote.entity.answer_vote;
import com.team23.PreProject.post_vote.entity.post_vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface answer_vote_repository extends JpaRepository<answer_vote,Integer> {
    Optional<answer_vote> findByMemberMemberIdAndAnswerAnswerId(Integer member_id, Integer answer_id);
}
