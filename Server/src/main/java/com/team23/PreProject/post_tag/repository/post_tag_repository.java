package com.team23.PreProject.post_tag.repository;

import com.team23.PreProject.post_tag.entity.post_tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface post_tag_repository extends JpaRepository<post_tag,Integer> {
    List<post_tag> findByPostPostId(Integer post_id);
}
