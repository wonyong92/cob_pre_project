package com.team23.PreProject.post_vote.repository;

import com.team23.PreProject.post_vote.entity.post_vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface post_vote_repository extends JpaRepository<post_vote,Integer> {
    Optional<post_vote> findByMemberMemberIdAndPostPostId(Integer member_id, Integer post_id);
}
