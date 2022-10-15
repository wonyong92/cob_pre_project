package com.team23.PreProject.answer.controller;

import com.team23.PreProject.answer.dto.MultiResponseDto;
import com.team23.PreProject.answer.dto.answer_dto;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.answer.mapper.answer_mapper;
import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.answer.service.answer_service;
import com.team23.PreProject.checkMember;
import com.team23.PreProject.member.repository.member_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController

@RequestMapping
public class answer_controller {
    @Autowired
    answer_service answerService;
    @Autowired
    answer_mapper mapper;
    @Autowired
    member_repository member_repository;

    @Autowired
    checkMember checkMember;
    @Autowired
    answer_repository answer_repository;


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("DBtest/createAnswer")
    public ResponseEntity postAnswer(
                                     @RequestBody answer_dto.Post RequestBody,
                                     @RequestHeader(value="Authorization") String token) {
        if(RequestBody.getMemberId() == 1 || !checkMember.checkMemberMemberId(RequestBody.getMemberId(), token))
        {
            return new ResponseEntity("fail",HttpStatus.FORBIDDEN);
        }
        answer savedAnswer = answerService.createAnswer(RequestBody);
        answer_dto.Response response = mapper.answerToAnswerResponseDto(savedAnswer);
        response.setMemberId(RequestBody.getMemberId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 정렬 기준 쿼리파라미터로 받아서 설정하는 기능 추후 추가 예정
    @GetMapping("DBtest/findAnswers/{questionId}")
    public ResponseEntity getAnswers(@PathVariable("questionId") Integer questionId,
                                     @RequestParam(defaultValue="1") int page,
                                     @RequestParam(defaultValue="1") int size) {
        Page<answer> pageAnswers = answerService.findAnswers(questionId, page-1,size);
        List<answer> answers = pageAnswers.getContent();
        return new ResponseEntity<>( new MultiResponseDto(mapper.answersToAnswerResponseDto(answers),pageAnswers) , HttpStatus.OK);
    }

    @GetMapping("DBtest/findAnswersBymember/{memberId}")
    public ResponseEntity getAnswersBymember(@PathVariable("memberId") Integer memberId,
                                     @Positive @RequestParam(defaultValue="1") int page,
                                             @Positive @RequestParam(defaultValue="15") int size) {
        answer_dto.ByMemberDto dto = answerService.findAnswersBymember(memberId, page-1,size);

        if(memberId <=1 || member_repository.findById(memberId).orElse(null)==null)
        {
            return new ResponseEntity<>( "wrong member" , HttpStatus.OK);
        }
        return new ResponseEntity<>( dto , HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("DBtest/updateAnswer/{answerId}")
    public ResponseEntity putAnswer(@PathVariable("answerId") Integer answerId, @RequestBody answer_dto.Post requestBody
            ,
                                    @RequestHeader(value="Authorization") String token) {
        if(requestBody.getMemberId() == 1 || !checkMember.checkMemberMemberId(requestBody.getMemberId(), token))
        {
            return new ResponseEntity("fail",HttpStatus.FORBIDDEN);
        }
        String content = requestBody.getContent();
        answer changedAnswer = answerService.updateAnswer(answerId, content);
        answer_dto.Response response = mapper.answerToAnswerResponseDto(changedAnswer);
        response.setMemberId(requestBody.getMemberId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("DBtest/deleteAnswer/{answerId}")

    public ResponseEntity deleteAnswer(@PathVariable("answerId") Integer answerId,

                                       @RequestHeader(value="Authorization") String token){
        try {
            Integer MemberId = answer_repository.findById(answerId).get().getMember().getMemberId();
            if (MemberId == 1 || !checkMember.checkMemberMemberId(MemberId, token)) {
                return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
            }
            String result = answerService.deleteAnswer(answerId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch(Exception e)
        {

            return new ResponseEntity(" false", HttpStatus.NOT_FOUND);
        }
            }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("DBtest/answerSelect")
    public ResponseEntity answerSel(@RequestParam Integer post_id,
                                    @RequestParam Integer answer_id,

                                    @RequestHeader(value="Authorization") String token
    )
    {
        try {
            Integer MemberId = answer_repository.findById(answer_id).get().getMember().getMemberId();
            if (MemberId == 1 || !checkMember.checkMemberMemberId(MemberId, token)) {
                return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
            }
            boolean result = answerService.select(post_id, answer_id);

            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
}


