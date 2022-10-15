package com.team23.PreProject.answer.repository;

import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.post.entity.post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface answer_repository extends JpaRepository<answer, Integer> {
    Page<answer> findAllByPost(post post, Pageable pageable);
}
