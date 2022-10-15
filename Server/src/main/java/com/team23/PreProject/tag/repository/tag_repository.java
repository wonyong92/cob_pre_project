package com.team23.PreProject.tag.repository;

import com.team23.PreProject.tag.entity.tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tag_repository extends JpaRepository<tag,Integer> {
    tag findByName(String name);
}
