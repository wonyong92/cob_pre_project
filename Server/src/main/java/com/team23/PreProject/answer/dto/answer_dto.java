package com.team23.PreProject.answer.dto;

import com.team23.PreProject.answer.entity.answer;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class answer_dto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        private Integer postId;
        private String content;
        private Integer memberId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Put{
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private long answerId;
        private String content;
        private ZonedDateTime createDate;
        private ZonedDateTime modified_date;
        private boolean Accepted;
        private long memberId;
        private String id;
        //   private String profileImageLink;
    }
    @Getter
    @Builder

    @NoArgsConstructor
    public static class ByMemberDto{
        PageInfo pageInfo;
        List<answer_info> answers;

        public ByMemberDto(PageInfo pageInfo,List<answer_info> answers)
        {
            this.pageInfo = pageInfo;
            this.answers = answers;
        }
        //   private String profileImageLink;
    }

}
