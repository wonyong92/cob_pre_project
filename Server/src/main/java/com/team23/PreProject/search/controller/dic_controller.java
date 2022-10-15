package com.team23.PreProject.search.controller;

import com.team23.PreProject.member_post.repository.member_post_repository;
import com.team23.PreProject.post.dto.member_info;
import com.team23.PreProject.post_tag.entity.post_tag;
import com.team23.PreProject.search.dto.answer_response;
import com.team23.PreProject.search.dto.dic_search_dto;
import com.team23.PreProject.search.repository.dic_repository;
import com.team23.PreProject.search.service.dic_service;
import com.team23.PreProject.post.dto.post_info;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post.repository.post_repository;
import com.team23.PreProject.tag.entity.tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class dic_controller {
    @Autowired
    dic_service dic_service;

    @Autowired
    dic_repository dic_repository;
    @Autowired
    post_repository post_repository;
    @Autowired
    com.team23.PreProject.member_post.repository.member_post_repository member_post_repository;


    @PostMapping("DBtest/dic")
    public String addDic(@RequestParam String name
                         )
    {
        Boolean result = dic_service.addDic(name);

        return "true";
    }

    @GetMapping("DBtest/search")
    public ResponseEntity search(@RequestParam String phrase)
    {
        dic_service.addDics(phrase);
        List<Object> pis = dic_service.searchAll(phrase);
        List<dic_search_dto> dtos = new ArrayList<>();

        List<post_info> result = new ArrayList<>();
        dic_search_dto dto = new dic_search_dto();
        for(Object obj : pis)
        {//오브젝트에서 값 가져오기!!!!!

            dto.setPost_id(Integer.parseInt( ((Object[])obj)[0].toString()));
            dto.setPost_name(((Object[])obj)[1].toString());
            dtos.add(dto);
            dto = new dic_search_dto();
        }

        for(dic_search_dto dt : dtos)
        {
            System.out.println(dt.getPost_name());
        }

        int dtos_size = dtos.size();
        int dtos_size_n = dtos_size;
        System.out.println("\ndtos_size size "+dtos_size+"\n ");

        System.out.println("\ndto mapping suc;\n");
        List<String> tokens = Arrays.asList(phrase.split(" "));
        System.out.println("\ntokens size "+tokens.size()+"\n ");
        Collections.sort(tokens,new sort(dic_repository));
        System.out.println("\nsort suc;\n");
        post_info post_info = new post_info();
        post post = new post();
        int count = 0;
        int find = 0;
        System.out.println();
        for(String tt : tokens)
        {
            System.out.println(tt);
        }
        System.out.println();
        //검색문 토큰들을 점수 순으로 정렬
        for(int i = 0 ; i<tokens.size();i++)
        { //System.out.println("\n1st;\n");

            for(int k = 0 ; k<dtos_size;k++) {
                //System.out.println("\n2nd;\n");
                for (int j = 0;j < tokens.size()-i ;j++) {
                    count++;
                   // System.out.println("\n3rd;\n");

                    if(dtos.get(k).getPost_name().equals("already inserted null%%")||dtos.get(k).getPost_name().indexOf(tokens.get(j))==-1) {
                        System.out.println("\n"+dtos.get(k).getPost_name()+" "+tokens.get(j)+" break;\n");
                        break;
                    }
                    else if(j==tokens.size()-i-1 && dtos.get(k).getPost_name().indexOf(tokens.get(j))!=-1)
                    {
                        if(!result.contains(dtos.get(k))) {
                            post = post_repository.findById(dtos.get(k).getPost_id()).get();
                            post_info = new post_info();

                            post_info.setPostId(post.getPostId());
                            post_info.setPost_content(post.getPost_content());
                            post_info.setPost_name(post.getPost_name());
                            post_info.setView_count(post.getView_count());
                            post_info.set_answered(post.getIs_answered());
                            post_info.setScore(post.getScore());
                            List<String> tags = new ArrayList<>();
                            for(com.team23.PreProject.post_tag.entity.post_tag pt : post.getPost_tags())
                            {
                                tags.add(pt.getTag().getName());
                            }
                            post_info.setTags(tags);
                            post_info.setWriteDate(post.getWrite_date());
                            post_info.setModifiedDate(post.getModified_date());
                            post_info.setAnswerCount(post.getAnswers().size());
                            result.add(post_info);
                            member_info mi = new member_info();
                            Integer memberId = member_post_repository.findByPostPostId(post.getPostId()).getMember().getMemberId();
                            mi.setMember_id(memberId);
                            mi.setProfile_id(memberId);
                            mi.setNickName(member_post_repository.findByPostPostId(post.getPostId()).getMember().getNickName());
                            post_info.setWriter(mi);
                            dtos.get(k).setPost_name("already inserted null%%");

                            System.out.println("\nfound\n");
                        }
                    }
                }
            }
        }
        System.out.println(" \n\n count "+count);
        answer_response response = new answer_response();
        response.setPosts(result);
        response.setQuestions(result.size());





        return new ResponseEntity(response, HttpStatus.OK);
    }
}
