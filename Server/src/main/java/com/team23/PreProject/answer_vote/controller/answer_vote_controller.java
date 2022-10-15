package com.team23.PreProject.answer_vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class answer_vote_controller {

    @Autowired
    com.team23.PreProject.answer_vote.service.answer_vote_service answer_vote_service;
    @Autowired
    com.team23.PreProject.checkMember checkMember;
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("DBtest/answer_vote")
    public ResponseEntity vote(@RequestParam Integer vote,
                               @RequestParam Integer member_id,
                               @RequestParam Integer answer_id
            ,
                               @RequestHeader(value="Authorization") String token)
    {
        if(member_id == 1 || !checkMember.checkMemberMemberId(member_id, token))
        {
            return new ResponseEntity("fail",HttpStatus.FORBIDDEN);
        }
        String result = answer_vote_service.vote(vote,member_id,answer_id);


        return new ResponseEntity(result,HttpStatus.OK);
    }//vote
}
