package com.team23.PreProject.member.repository;

import com.team23.PreProject.member.entity.member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface member_repository extends JpaRepository<member,Integer> {
    member findByProfileProfileId(Integer profile_id);

    member findByid(String id);

    member findByNickName(String username);
}
