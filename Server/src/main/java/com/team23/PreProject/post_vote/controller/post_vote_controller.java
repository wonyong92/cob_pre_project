package com.team23.PreProject.post_vote.controller;

import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.post.repository.post_repository;
import com.team23.PreProject.post.service.post_service;
import com.team23.PreProject.post_vote.entity.post_vote;
import com.team23.PreProject.post_vote.repository.post_vote_repository;
import com.team23.PreProject.post_vote.service.post_vote_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class post_vote_controller {

    @Autowired
    post_vote_service post_vote_service;
    @Autowired
    com.team23.PreProject.checkMember checkMember;
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("DBtest/post_vote")
    public ResponseEntity vote(@RequestParam Integer vote,
                               @RequestParam Integer member_id,
                               @RequestParam Integer post_id,
                               @RequestHeader(value="Authorization") String token)
    {
        if (member_id == 1 || !checkMember.checkMemberMemberId(member_id, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        String result = post_vote_service.vote(vote,member_id,post_id);


        return new ResponseEntity(result,HttpStatus.OK);
    }//vote
}
