package com.team23.PreProject.member_post.repository;

import com.team23.PreProject.member_post.entitiy.member_post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface member_post_repository extends JpaRepository<member_post,Integer> {
    member_post findByPostPostId(Integer post_id);
}
