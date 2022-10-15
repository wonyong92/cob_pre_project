package com.team23.PreProject.post.controller;

import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.member_post.repository.member_post_repository;
import com.team23.PreProject.post.dto.*;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.service.post_service;
import com.team23.PreProject.post_tag.entity.post_tag;
import com.team23.PreProject.post_tag.repository.post_tag_repository;
import com.team23.PreProject.tag.repository.tag_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController

public class post_controller {
    @Autowired
    post_service post_service;
    @Autowired
    member_repository member_repository;
    @Autowired
    member_post_repository member_post_repository;
    @Autowired
    member_service member_service;
    @Autowired
    tag_repository tag_repository;
    @Autowired
    post_tag_repository post_tag_repository;
    @Autowired
    com.team23.PreProject.checkMember checkMember;


    @GetMapping(value = "/")
    public ResponseEntity main_page() {
        System.out.println("main page request " + LocalDate.now() +" "+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH시 mm분 ss초")));
        return new ResponseEntity("main page ",HttpStatus.OK);
    }

    @GetMapping("/DBtest/getPost")
    public ResponseEntity getPost(@RequestParam Integer post_id)

    {

        post_get_dto dto = post_service.getPost(post_id);

        if(dto==null)
            return new ResponseEntity("not found",HttpStatus.NOT_FOUND);

        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/DBtest/post")
    public ResponseEntity createPost(@RequestBody post_insert_dto test,
                                     @RequestHeader(value="Authorization") String token)
    {
        if (test.getMember_id() == 1 || !checkMember.checkMemberMemberId(test.getMember_id(), token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        post post = post_service.create_post(test);

        post_create_dto dto = new post_create_dto(post.getPostId(),post.getPost_content(),post.getPost_content(),post.getWrite_date(),post.getModified_date());
        System.out.println("dto created");
        return new ResponseEntity(dto,HttpStatus.CREATED);
    }

    @GetMapping("/DBtest/findAllPost")
    public ResponseEntity findAll(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(required = false, value = "size", defaultValue = "15") Integer size){
        page = page -1;
        System.out.println("find all request");
        post_all_dto post_list =post_service.findAllPost(page,size);


        post_all dto = new post_all();

        post_info post_info;


        dto.setTotalPages(post_list.getPage().getTotalPages());
        dto.setQuestions(post_list.getPage().getTotalElements());
        dto.setPageNumber(post_list.getPage().getPageable().getPageNumber()+1);
        dto.setSortBy("post_id");
        dto.setSize(size);

        for(Object obj : post_list.getPage().getContent() )
        {
            post post = (post) obj;
            post_info = new post_info();

            post_info.setPostId(post.getPostId());
            post_info.setPost_content(post.getPost_content());
            post_info.setPost_name(post.getPost_name());
            post_info.setView_count(post.getView_count());
            post_info.set_answered(post.getIs_answered());
            post_info.setScore(post.getScore());

            List<post_tag> post_tags  = post.getPost_tags();
            for(int i = 0; i<post_tags.size();i++)
            {
                post_info.addTag(tag_repository.findById(
                        post_tags.get(i).getTag().getId()).get().getName());
            }

            dto.addPost_info(post_info);

            member_info member_info = new member_info();
            member member = member_post_repository.findByPostPostId(post.getPostId()).getMember();
            member_info.setMember_id(member.getMemberId());
            member_info.setNickName(member.getNickName());
            member_info.setProfile_id(member.getProfile().getProfileId());

            post_info.setWriter(member_info);
            post_info.setModifiedDate(post.getModified_date());
            post_info.setWriteDate(post.getWrite_date());

        }







        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/DBtest/findPost/{member_id}")
    public ResponseEntity findAll(@RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(required = false, value = "size", defaultValue = "15") Integer size,
                                    @PathVariable Integer member_id)
    {

        if(member_id == 1)
            return new ResponseEntity("you tried to access deleted user", HttpStatus.CONFLICT);

        page = page -1;
        Page post_list =post_service.findPostByMember(page,size,member_id);


        post_all dto = new post_all();

        post_info post_info;


        dto.setTotalPages(post_list.getTotalPages());
        dto.setQuestions(post_list.getTotalElements());
        dto.setPageNumber(post_list.getPageable().getPageNumber()+1);
        dto.setSortBy("post_id");
        dto.setSize(size);

        for(Object obj : post_list.getContent() )
        {
            post post = (post) obj;
            post_info = new post_info();

            post_info.setPostId(post.getPostId());
            post_info.setPost_content(post.getPost_content());
            post_info.setPost_name(post.getPost_name());
            post_info.setView_count(post.getView_count());
            post_info.set_answered(post.getIs_answered());
            post_info.setScore(post.getScore());

            List<post_tag> post_tags  = post.getPost_tags();
            for(int i = 0; i<post_tags.size();i++)
            {
                post_info.addTag(tag_repository.findById(
                        post_tags.get(i).getTag().getId()).get().getName());
            }

            dto.addPost_info(post_info);

            member_info member_info = new member_info();
            member member = member_post_repository.findByPostPostId(post.getPostId()).getMember();
            member_info.setMember_id(member.getMemberId());
            member_info.setNickName(member.getNickName());
            member_info.setProfile_id(member.getProfile().getProfileId());

            post_info.setWriter(member_info);
            post_info.setModifiedDate(post.getModified_date());
            post_info.setWriteDate(post.getWrite_date());


        }



        return new ResponseEntity(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/DBtest/update/{post_id}")

    public ResponseEntity updatePost(@PathVariable Integer post_id,
                                     @RequestBody post_update_dto dto,
                                     @RequestHeader(value="Authorization") String token
                                     )
    {
        if(member_post_repository.findByPostPostId(post_id).getMember().getMemberId()==1)
            return new ResponseEntity("you tried access deleted user",HttpStatus.CONFLICT);
        Integer MemberId = member_post_repository.findByPostPostId(post_id).getMember().getMemberId();
        if (MemberId == 1 || !checkMember.checkMemberMemberId(MemberId, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }

        post post = post_service.updatePost(post_id,dto);
        postWithTag result = new postWithTag();
        result.setPostInfo(post);

        System.out.println("\n\n\n\n\nresult set post\n\n\n\n");
        List<post_tag> tags = post_tag_repository.findByPostPostId(post.getPostId());
        System.out.println("\n\n\n\n\nget tags\n\n\n\n"+tags.get(0).getTag().getName());

        for(post_tag tag : tags)
        {
            result.addTag(tag.getTag().getName());
            System.out.println("\n\n\n\n\nadd tag name\n\n\n\n");
        }


        if(post!=null)
            return new ResponseEntity(result,HttpStatus.OK);
        else
            return new ResponseEntity("error while update post",HttpStatus.CONFLICT);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/DBtest/delete/{post_id}")
    public ResponseEntity deletePost(@PathVariable Integer post_id   ,
                                     @RequestHeader(value="Authorization") String token                                  )
    {

        if(member_post_repository.findByPostPostId(post_id)!=null && member_post_repository.findByPostPostId(post_id).getMember().getMemberId()==1)
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        Integer MemberId = member_post_repository.findByPostPostId(post_id).getMember().getMemberId();
        if (MemberId == 1 || !checkMember.checkMemberMemberId(MemberId, token)) {
            return new ResponseEntity("fail", HttpStatus.FORBIDDEN);
        }
        System.out.println("deleted post content "+ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        String result = post_service.deletePost(post_id);



        return new ResponseEntity(result,HttpStatus.OK);

    }






}
