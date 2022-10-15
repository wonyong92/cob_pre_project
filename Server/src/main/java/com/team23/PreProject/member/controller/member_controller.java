package com.team23.PreProject.member.controller;

import com.team23.PreProject.member.dto.member_create_dto;
import com.team23.PreProject.member.dto.member_password_update_dto;
import com.team23.PreProject.member.dto.tokenLogin;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.post.entity.post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Random;

@RestController

public class member_controller {
    @Autowired
    member_service member_service;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    com.team23.PreProject.checkMember checkMember;
    @Autowired
    com.team23.PreProject.member.repository.member_repository member_repository;


    @PermitAll
    @PostMapping("DBtest/createMember")
    public ResponseEntity insertMember(@RequestBody member_create_dto member_dto)
    {
        if(member_service.findMemberById(1)==null)
        {
            member member = member_service.insert_member(new member_create_dto("deleted","deleted","deleted"));
        }
        Random rd = new Random();
        member_dto.setNickName("nickname"+rd.nextInt(10000));
        member member = member_service.insert_member(member_dto);

        return new ResponseEntity(member,HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("DBtest/findMember/{member_id}")
    public ResponseEntity findMember(@PathVariable Integer member_id)
    {
//        if(member_id == 1)
//        {
//            return new ResponseEntity("not found",HttpStatus.NOT_FOUND);
//        }
        member member = member_service.findMemberById(member_id);

        if(member!=null)
        return new ResponseEntity(member,HttpStatus.FOUND);
        else
            return new ResponseEntity("not found",HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("DBtest/updatePassword")
    public ResponseEntity updatePassword(@RequestParam Integer member_id,
                                         @RequestBody member_password_update_dto dto,
                                         @RequestHeader(value="Authorization") String token
    )
    {
        if (member_id == 1 || !checkMember.checkMemberMemberId(member_id, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        String result = member_service.updatePassword(member_id,dto.getElder(),dto.getNewer());
        if(result.equals("passwored changed"))
            return new ResponseEntity("passwored changed",HttpStatus.OK);
        else
            return new ResponseEntity("passwored not changed",HttpStatus.CONFLICT);
    }//updatePassword end



    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("DBtest/deleteMember")
    public ResponseEntity deleteMember(@RequestParam Integer member_id,
                                       @RequestHeader(value="Authorization") String token)
    {
        if (member_id == 1 || !checkMember.checkMemberMemberId(member_id, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        String result = member_service.deleteMember(member_id);

        if(result.equals("delete suc"))
            return new ResponseEntity(result,HttpStatus.OK);
        else
            return new ResponseEntity(result,HttpStatus.CONFLICT);
    }//deleteMember

    @GetMapping("DBtest/checkExistId")
    public ResponseEntity checkId(@RequestParam String id)
    {
        boolean result = false;
        result = member_service.checkExistId(id);
        if(result)
            return new ResponseEntity("false",HttpStatus.OK);
        else
            return new ResponseEntity("true",HttpStatus.OK);
    }

    @GetMapping("DBtest/tokenLogin")//로그인 체크 - true == 로그인된 토큰, 로그인 상태 <> false == 로그아웃된 토큰, 잘못된 토큰, 로그아웃 상태
    public ResponseEntity jwtTest(@RequestHeader("Authorization") String token)
    {
        boolean logouted = member_service.checkLogout(token);//로그아웃 된 토큰인지 확인
        System.out.println("===================================result "+logouted+"\n\n");
        if(logouted)
        {
            return new ResponseEntity("false",HttpStatus.OK);//로그아웃된 토큰
        }

        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("not logout infromation"+nickname+"\n");
        tokenLogin dto = new tokenLogin("true", nickname, member_repository.findByid(nickname).getMemberId());
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("DBtest/Logout")
    public ResponseEntity jwtTestlogout(@RequestHeader("Authorization") String token)
    {
        String result = member_service.logout(token);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("DBtest/refreshToken")//로그인 수행후 바로 요청되어야하는 api - 해당 토큰 활성화
    public ResponseEntity refreshToken(@RequestHeader("Authorization") String token)
    {
       member_service.refresh(token);
       return new ResponseEntity("{\"msg\":\"true\"}",HttpStatus.OK);
    }


}
