package com.team23.PreProject.profile.service;

import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.profile.dto.profile_update_dto;
import com.team23.PreProject.profile.entity.profile;
import com.team23.PreProject.profile.repository.profile_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class profile_service {
    @Autowired
    profile_repository profile_repository;
    @Autowired
    member_repository member_Repository;
    public profile findProfile(String id) {
        profile profile = member_Repository.findByid(id).getProfile();
        return profile;
    }

    public profile update(Integer profile_id , profile_update_dto dto) {
        profile profile = profile_repository.findById(profile_id).get();
        profile.setAbout(dto.getAbout());
        profile.setLocation(dto.getLocation());
        if(!dto.getDisplayname().equals("")) {
            profile.setDisplayname(dto.getDisplayname());
            member member = member_Repository.findByProfileProfileId(profile_id);
            member.setNickName(dto.getDisplayname());
            member_Repository.save(member);

        }
        profile = profile_repository.save(profile);

        return profile;
    }
}
