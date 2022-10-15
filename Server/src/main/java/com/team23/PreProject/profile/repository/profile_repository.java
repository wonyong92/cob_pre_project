package com.team23.PreProject.profile.repository;

import com.team23.PreProject.profile.entity.profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface profile_repository extends JpaRepository<profile, Integer> {
    //profile findByMemberMemberId(Integer member_id);
}
