package com.team23.PreProject.FIle.controller;

import com.team23.PreProject.FIle.dto.FileDto;

import com.team23.PreProject.FIle.dto.download_dto;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.annotation.security.PermitAll;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class controller {

    @Autowired
    member_repository member_repository;
    @Autowired
    com.team23.PreProject.checkMember checkMember;
    @Value("${spring.servlet.multipart.location}")
    String filePath;
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/DBtest/upload")
// 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    //여러개 업로드
    public ResponseEntity upload(@RequestParam MultipartFile file,@RequestParam Integer memberId
                         ,@RequestHeader(value="Authorization") String token ) throws IllegalStateException, IOException {
        if (memberId == 1 || !checkMember.checkMemberMemberId(memberId, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        FileDto dto = null;

        member member = member_repository.findById(memberId).orElse(null);
        if(member==null)
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);

        File newFileName = null;
        if (!file.isEmpty()) {
            // UUID를 이용해 unique한 파일 이름을 만들어준다.
            dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());


            newFileName = new File(dto.getUuid() + "_" + dto.getFileName()+".png");
            // 전달된 내용을 실제 물리적인 파일로 저장해준다.
            file.transferTo(newFileName);
        }
        member.setProfileImage(newFileName.getName());
        member_repository.flush();
        System.out.println("\n\n\n\n"+newFileName.getName()+"\n\n\n\n");




        return new ResponseEntity("suc", HttpStatus.OK);
    }



    @PermitAll
    @GetMapping("/DBtest/download")
    public ResponseEntity download(@RequestParam Integer memberId
            ) throws IOException {


        System.out.println("download request");
        String filename;
        if(member_repository.findById(memberId).orElse(null) == null)
            filename = "default.png";
        else
            filename = member_repository.findById(memberId).get().getProfileImage();

        Path path = Paths.get(filePath + "/" + filename);
        System.out.println(path);
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(filename, StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/DBtest/downloadDefaultImg")
    public ResponseEntity<Resource> defaultImg() throws IOException {
        System.out.println("download request");
        Path path = Paths.get(filePath + "/" + "default.png");
        System.out.println(path);
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("default.png", StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


}
