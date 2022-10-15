package com.team23.PreProject.post.repository;

import com.team23.PreProject.post.entity.post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface post_repository extends JpaRepository<post,Integer> {
    Page<post> findByPostIdIn(List<Integer> postIds, Pageable pageable);

}
