package com.team23.PreProject;

import com.team23.PreProject.answer.controller.answer_controller;
import com.team23.PreProject.answer.dto.answer_dto;
import com.team23.PreProject.answer.service.answer_service;
import com.team23.PreProject.comment.service.comment_service;
import com.team23.PreProject.member.controller.member_controller;
import com.team23.PreProject.member.dto.member_create_dto;
import com.team23.PreProject.member.repository.member_repository;
import com.team23.PreProject.member.service.member_service;
import com.team23.PreProject.post.controller.post_controller;
import com.team23.PreProject.post.dto.post_insert_dto;
import com.team23.PreProject.post.service.post_service;
import com.team23.PreProject.tag.dto.tag_create;
import com.team23.PreProject.tag.entity.tag;
import com.team23.PreProject.tag.service.tag_service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PreProjectApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {

		SpringApplication.run(PreProjectApplication.class, args);
	}


	@Bean
	public CommandLineRunner test(member_service mem_serv, post_service post_serv, answer_service ans_serv, tag_service tag_ser, comment_service com_ser) {
		return args -> {
			System.out.println("\n\n************************************************ insert member start \n\n\n");
			mem_serv.insert_member(new member_create_dto("password","deleted","deleted"));
//			mem_serv.insert_member(new member_create_dto("password","deleted","deleted")).setRoles("");
			mem_serv.insert_member(new member_create_dto("password","nick","Iddddddd1"));
			System.out.println("\n\ninsert member end ************************************************\n\n\n");
			List<String> tags = new ArrayList<>();
			System.out.println("\n\n************************************************ insert tag start \n\n\n");
			tags.add(tag_ser.createTag(new tag_create("java","test tag for name is java version 10" ,null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java1","test tag for name is java version 11",null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java2","test tag for name is java version 12",null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java3","test tag for name is java version 13",null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java4","test tag for name is java version 14",null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java5","test tag for name is java version 15",null)).getName());
			tags.add(tag_ser.createTag(new tag_create("java6","test tag for name is java version 16",null)).getName());

			tag_ser.createTag(new tag_create("javascript" ,"",null));
			tag_ser.createTag(new tag_create("java" ,"",null));
			tag_ser.createTag(new tag_create("react" ,"",null));
			tag_ser.createTag(new tag_create("react-redux" ,"",null));
			tag_ser.createTag(new tag_create("api" ,"",null));
			tag_ser.createTag(new tag_create("python" ,"",null));
			tag_ser.createTag(new tag_create("spring" ,"",null));
			tag_ser.createTag(new tag_create("git" ,"",null));
			tag_ser.createTag(new tag_create("react-hooks" ,"",null));
			tag_ser.createTag(new tag_create("react-router-dom" ,"",null));
			tag_ser.createTag(new tag_create("css" ,"",null));
			tag_ser.createTag(new tag_create("html" ,"",null));
			tag_ser.createTag(new tag_create("git" ,"",null));
			tag_ser.createTag(new tag_create("github" ,"",null));
			tag_ser.createTag(new tag_create("jquery" ,"",null));
			tag_ser.createTag(new tag_create("flutter" ,"",null));
			tag_ser.createTag(new tag_create("firebase" ,"",null));
			tag_ser.createTag(new tag_create("node.js" ,"",null));
			tag_ser.createTag(new tag_create("typescript" ,"",null));
			tag_ser.createTag(new tag_create("c" ,"",null));
			tag_ser.createTag(new tag_create("c#" ,"",null));


			System.out.println("\n\ninsert tag end ************************************************\n\n\n");

			System.out.println("\n\n************************************************ insert post start \n\n\n");
			String obj;
			obj= "java";
			post_serv.create_post(new post_insert_dto("how to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to delete "+obj,"post_content1",2, tags));

			post_serv.create_post(new post_insert_dto("i need create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("i need update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("i need read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("i need delete "+obj,"post_content1",2, tags));

			post_serv.create_post(new post_insert_dto("docs to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("docs to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("docs to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("docs to delete "+obj,"post_content1",2, tags));
			obj= "react";
			post_serv.create_post(new post_insert_dto("how to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to delete "+obj,"post_content1",2, tags));
			obj= "python";
			post_serv.create_post(new post_insert_dto("how to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to delete "+obj,"post_content1",2, tags));
			obj= "git";
			post_serv.create_post(new post_insert_dto("how to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to delete "+obj,"post_content1",2, tags));
			obj= "css";
			post_serv.create_post(new post_insert_dto("how to create "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to update "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to read "+obj,"post_content1",2, tags));
			post_serv.create_post(new post_insert_dto("how to delete "+obj,"post_content1",2, tags));




			System.out.println("\n\ninsert member end ************************************************\n\n\n");
//			private Integer postId;
//			private String content;
//			private Integer memberId;
			System.out.println("\n\n************************************************ insert answer start \n\n\n");
			ans_serv.createAnswer(new answer_dto.Post(1,"answer for question 1",2));
			ans_serv.createAnswer(new answer_dto.Post(2,"answer for question 2",2));
			ans_serv.createAnswer(new answer_dto.Post(3,"answer for question 3",2));
			ans_serv.createAnswer(new answer_dto.Post(2,"answer 2 for question 2",2));
			System.out.println("\n\ninsert answer end ************************************************\n\n\n");

			System.out.println("\n\n************************************************ insert answer comment start \n\n\n");
			com_ser.createAnswerComment(1,2,"1. comment for answerId 1");
			com_ser.createAnswerComment(1,2,"2. comment for answerId 1");
			com_ser.createAnswerComment(2,2,"1. comment for answerId 2");
			com_ser.createAnswerComment(2,2,"2. comment for answerId 2");
			com_ser.createAnswerComment(3,2,"1. comment for answerId 3");
			com_ser.createAnswerComment(3,2,"2. comment for answerId 3");
			System.out.println("\n\ninsert answer comment end ************************************************\n\n\n");

			System.out.println("\n\n************************************************ insert post comment start \n\n\n");
			com_ser.createPostComment(1,2,"1. comment for postId 1");
			com_ser.createPostComment(1,2,"2. comment for postId 1");
			com_ser.createPostComment(2,2,"1. comment for postId 2");
			com_ser.createPostComment(2,2,"2. comment for postId 2");
			com_ser.createPostComment(3,2,"1. comment for postId 3");
			com_ser.createPostComment(3,2,"2. comment for postId 3");
			System.out.println("\n\ninsert post comment end ************************************************\n\n\n");





			// 이것저것...
		};
	}

}
