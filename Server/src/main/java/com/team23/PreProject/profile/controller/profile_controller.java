package com.team23.PreProject.profile.controller;

import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.profile.dto.getToken;
import com.team23.PreProject.profile.dto.profile_dto;
import com.team23.PreProject.profile.dto.profile_update_dto;
import com.team23.PreProject.profile.entity.profile;
import com.team23.PreProject.profile.repository.profile_repository;
import com.team23.PreProject.profile.service.profile_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@RestController
public class profile_controller {
    @Autowired
    profile_service profile_service;
    @Autowired
    com.team23.PreProject.member.service.member_service member_service;
    @Autowired
    member_repository memberRepository;
    @Autowired
    com.team23.PreProject.checkMember checkMember;
    @Autowired
    profile_repository profile_repository;
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/DBtest/getProfile")
    public ResponseEntity getMemberProfile(HttpServletRequest request,
                                           @RequestHeader(value="Authorization") String token)

    {
        System.out.println("=========================================="+request.getHeader("Authorization")+"\n\n\n");

        profile pro = profile_service.findProfile(SecurityContextHolder.getContext().getAuthentication().getName());



        return new ResponseEntity<>(pro,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/DBtest/updateProfile")
    public ResponseEntity updateProfile(@RequestParam Integer profile_id,
                                        @RequestBody profile_update_dto dto,
                                        @RequestHeader(value="Authorization") String token){
        try {

            if ( profile_id==1 || !checkMember.checkMemberMemberId(profile_id, token)){
                return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
            }
            profile profile = profile_service.update(profile_id, dto);

            return new ResponseEntity(profile, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
    }


}
