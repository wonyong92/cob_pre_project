package com.team23.PreProject.post.service;

import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.comment.repository.comment_repository;
import com.team23.PreProject.search.service.dic_service;
import com.team23.PreProject.member.entity.member;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member_post.entitiy.member_post;
import com.team23.PreProject.member_post.repository.member_post_repository;
import com.team23.PreProject.post.dto.*;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.repository.post_repository;

import com.team23.PreProject.post_tag.entity.post_tag;
import com.team23.PreProject.post_tag.repository.post_tag_repository;
import com.team23.PreProject.tag.repository.tag_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class post_service {
    @Autowired
    answer_repository answer_repository;
    @Autowired
   post_repository post_repository;
    @Autowired
    member_repository member_repository;
    @Autowired
    member_post_repository member_post_repository;

    @Autowired
    post_tag_repository post_tag_repository;

    @Autowired
    comment_repository comment_repository;
    @Autowired
    tag_repository tag_repository;
    @Autowired
    dic_service dic_service;

    public post create_post(post_insert_dto test){

            System.out.println("\n\n============================================ json parse");
            System.out.println("test "+test.getPost_content());
            System.out.println("test "+test.getMember_id());

            System.out.println("============================================ json parse end\n\n");
            //post 생성 - 본문, 이름, 유저 정보 저장
            post post= new post();
            post.setPost_content(test.getPost_content());
            post.setPost_name(test.getPost_name());


            //연관된 member 찾아오기
            System.out.println("============================================ find member entity");
            member member = member_repository.findById(test.getMember_id()).get();
            System.out.println("============================================ find member entity end\n\n");
            //연관된 member_post 정보 생성
            member_post member_post = new member_post();
            //연관된 post_tag 정보 생성


            member_post.setMember(member);
            member_post.setPost(post);

            System.out.println("============================================ save post entity");
            post_repository.save(post);
            System.out.println("============================================ save post entity end\n\n");

            System.out.println("============================================ save member_post entity");
            member_post_repository.save(member_post);
            System.out.println("============================================ save member_post entity end\n\n");

            if(test.getTags().size()!=0) {
                for (int i = 0;i < test.getTags().size();i++) {
                    post_tag post_tag = new post_tag();
                    post_tag.setPost(post);
                    post_tag.setTag(tag_repository.findByName(test.getTags().get(i)));
                    post.addPost_tag(post_tag);
                    post_tag_repository.save(post_tag);
                }
            }




            //연관된 member_post 정보를 post에 저장
            post.addMember_Post(member_post);

            //연관된 post_tag 정보를 post에 저장



            //연관된 member_post 정보를 member에 저장
            member.addMember_Post(member_post);

            //member.setMember_post_id(member_post.getMember_post_id());
            //post.setMember_post_id(member_post.getMember_post_id());
            //member 업데이트
            System.out.println("============================================ update member entity");
            member_repository.flush();
            System.out.println("============================================ update member entity end\n\n");

            //post 업데이트
            System.out.println("============================================ update post entity");
            post_repository.flush();
            System.out.println("============================================ update post entity end\n\n");

            dic_service.addDics(post.getPost_name());

            return post;

    }

    public post_all_dto findAllPost(int page, int size){
        if(size==-2 || page ==-2)
        {
            page = 0;
            size = (int) post_repository.count();
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("postId").descending());
        Page<post> post_list = post_repository.findAll(pageable);
        System.out.println("\n\n\n\n\n\n %%%%%%%%%%%%%%%%%%%%%%% post_list size "+post_list.getContent().size()+"\n\n\n\n");
        Long count = post_repository.count();

        post_all_dto dto = new post_all_dto(post_list,count);
        return dto;
    }

    public Page<post> findPostByMember(int page, int size,Integer member_id){



        //유저 정보 먼저 찾기
        member member = member_repository.findById(member_id).get();
        //
        List<Integer> post_ids = new ArrayList<>();

        for(member_post member_post: member.getMember_posts()){
            post_ids.add(member_post.getPost().getPostId());

        }
        if(size==-2 || page ==-2)
        {
            page = 0;
            size = (int) post_ids.size();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("postId").descending());
        Page<post> post_list = post_repository.findByPostIdIn(post_ids,pageable);

        return post_list;
    }


    public post updatePost(Integer post_id, post_update_dto dto) {


        try{
            post post = post_repository.findById(post_id).get();

            post.setModified_date(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
            post.setPost_content(dto.getPost_content());
            post.setPost_name(dto.getPost_name());


            List<post_tag> older = post.getPost_tags();
            for(post_tag post_tag : older)
            {
                post_tag_repository.delete(post_tag);
            }
            post.setPost_tags(new ArrayList<>());
            System.out.println("===========================posttag del\n\n\n\n\n");
            for(String tag : dto.getTags())
            {

                post_tag post_tag = new post_tag();
                post_tag.setTag(tag_repository.findByName(tag));
                post_tag.setPost(post);
                post_tag_repository.save(post_tag);
                post.addPost_tag(post_tag);
            }
            System.out.println("===========================posttag update\n\n\n\n\n\n");




            post_repository.save(post);
            return post;
        }catch(Exception e)
        {
            return null;
        }

    }

    public String deletePost(Integer post_id){
        boolean result = false;
        try {
            post post = post_repository.findById(post_id).orElse(null);

            if(post!=null)
            {
                post.setMember_posts(null);
                post.setPost_tags(null);
            post_repository.flush();
            member_post member_post = member_post_repository.findByPostPostId(post_id);
            member_post.setPost(null);
            member_post.setMember(null);
            member_post_repository.flush();
            member_post_repository.delete(member_post);



                List<post_tag> post_tags = post_tag_repository.findByPostPostId(post_id);
            for(post_tag tag: post_tags)
            {
                tag.setPost(null);
                tag.setTag(null);
                post_tag_repository.flush();
                post_tag_repository.delete(tag);
            }
//
            post_repository.delete(post);

            return "post deleted";
            }
            else
            {
               return "you tired to delete not existing post";
            }
        }catch(Exception e)
        {
            return "delete post error";
        }



    }//del end

    public post_get_dto getPost(Integer post_id)
    {

            post post = post_repository.findById(post_id).get();
            post.setView_count(post.getView_count()+1);
            post_repository.flush();
            System.out.println(post.getPost_name()+"===========================\n\n");
            member member = member_post_repository.findByPostPostId(post_id).getMember();
            post_get_dto dto;
            if(member.getMemberId()==1)
            {
                System.out.println("deleted access ===========================\n\n");
                dto = new post_get_dto(post,null,post_tag_repository.findByPostPostId(post_id), member_post_repository,answer_repository,comment_repository);
            }
            System.out.println("\n\n\n"+post_tag_repository.findByPostPostId(post_id).size()+"\n\n\n\n");
            dto = new post_get_dto(post,member.getProfile(),post_tag_repository.findByPostPostId(post_id),member_post_repository,answer_repository,comment_repository);


            return dto;






    }//get end

}
