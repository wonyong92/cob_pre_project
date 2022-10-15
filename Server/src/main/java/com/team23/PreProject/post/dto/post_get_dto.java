package com.team23.PreProject.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team23.PreProject.answer.entity.answer;
import com.team23.PreProject.answer.repository.answer_repository;
import com.team23.PreProject.comment.entity.comment;
import com.team23.PreProject.comment.repository.comment_repository;
import com.team23.PreProject.member_post.repository.member_post_repository;
import com.team23.PreProject.post.entity.post;
import com.team23.PreProject.post_tag.entity.post_tag;
import com.team23.PreProject.profile.entity.profile;
import com.team23.PreProject.tag.entity.tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class post_get_dto {

    @JsonIgnore
    answer_repository answer_repository;
    @JsonIgnore
    member_post_repository member_post_repository;

    post_info post =new post_info();
    profile_info profile = new profile_info();

    @JsonIgnore
    member_info writer = new member_info();

    public post_get_dto(post post, profile profile, List<post_tag> ptags, member_post_repository repo, answer_repository arepo, comment_repository crepo) {
        this.member_post_repository = repo;
        this.answer_repository = arepo;
        System.out.println("new dtocccc ===========================\n\n"+member_post_repository
                .findByPostPostId(
                        post.getPostId())
                .getMember()
                .getNickName());

        writer.setNickName(member_post_repository.findByPostPostId(post.getPostId()).getMember().getNickName());
        System.out.println("new dtocccc111 ===========================\n\n");
        writer.setProfile_id(member_post_repository.findByPostPostId(post.getPostId()).getMember().getMemberId());
        writer.setMember_id(member_post_repository.findByPostPostId(post.getPostId()).getMember().getMemberId());
        //this.post = new post_info();

        this.post.setPost_name(post.getPost_name());
        this.post.setPost_content(post.getPost_content());
        this.post.setPostId(post.getPostId());
        this.post.setWriter(writer);
        this.post.setWriteDate(post.getWrite_date());
        this.post.setModifiedDate(post.getModified_date());

        member_info mi = new member_info();
        List<answer> ansList = member_post_repository.findByPostPostId(post.getPostId()).getPost().getAnswers();
        List<comment> coList= new ArrayList<>();
        answer_info ai=new answer_info();
        comment_info ci = new comment_info();
        this.post.setAnswerCount(ansList.size());
        for(answer atemp : ansList)
        {
            ai = new answer_info();
            mi = new member_info();
            mi.setMember_id(atemp.getMember().getMemberId());
            mi.setNickName(atemp.getMember().getNickName());
            mi.setProfile_id(atemp.getMember().getProfile().getProfileId());
            ai.setMember(mi);
            ai.setScore(atemp.getScore());
            ai.setModifiedDate(atemp.getModifiedDate());
            ai.setWriteDate(atemp.getWriteDate());
            ai.setAnswer_content(atemp.getAnswer_content());
            ai.setAnswerId(atemp.getAnswerId());
            coList = crepo.findAllByAnswer(atemp);
            for(comment ctemp : coList)
            {
                ci=new comment_info();
                mi = new member_info();
                mi.setMember_id(ctemp.getMember().getMemberId());
                mi.setNickName(ctemp.getMember().getNickName());
                mi.setProfile_id(ctemp.getMember().getProfile().getProfileId());
                ci.setWriter(mi);
                ci.set_update(ctemp.getIs_update());
                ci.setContent(ctemp.getContent());
                ci.setCommentId(ctemp.getCommentId());
                ci.setModified_date(ctemp.getModified_date());
                ci.setCreateDate(ctemp.getCreateDate());
                ai.getAnswer_comments().add(ci);
            }


        }



        this.post.getAnswers().add(ai);
        List<comment> pcList = crepo.findAllByPost(post);
        for(comment pc : pcList)
        {
            ci = new comment_info();
            ci.setWriter(mi);
            ci.set_update(pc.getIs_update());
            ci.setContent(pc.getContent());
            ci.setCommentId(pc.getCommentId());
            ci.setModified_date(pc.getModified_date());
            ci.setCreateDate(pc.getCreateDate());
            this.post.getComments().add(ci);
        }

        //this.profile = new profile_info();
        System.out.println("new dtocccc222===========================\n\n");
        this.profile.setProfile_id(member_post_repository.findByPostPostId(post.getPostId()).getMember().getMemberId());
        this.profile.setDisplayname(member_post_repository.findByPostPostId(post.getPostId()).getMember().getNickName());

       // this.tags = new ArrayList<>();

        System.out.println("new dtocccc333 ===========================\n\n");
        for(post_tag pt : ptags)
        {
            System.out.println("new dto ===========================\n\n");
           System.out.println("new dto ===========================\n\n");
           this.post.tags.add(pt.getTag().getName());

        }
        this.post.setScore(post.getScore());
        this.post.set_answered(post.getIs_answered());
        this.post.setView_count(post.getView_count());
    }
}
