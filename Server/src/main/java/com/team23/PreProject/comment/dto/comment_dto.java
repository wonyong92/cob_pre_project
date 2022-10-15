package com.team23.PreProject.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

public class comment_dto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostAnswer{
        private Integer answerId;
        private Integer memberId;
        private String content;
//        "memberId":""
//        "content":""
//        "answerId":""
    }

    @Getter

    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostPost{
        private Integer postId;
        private Integer memberId;
        private String content;
    }
    @Getter
    @NoArgsConstructor
    public static class Put{
        private String content;
        private Integer memberId;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostResponse {
        private Integer commentId;
        private String content;
        private String id;
        private String profileImageLink;
        private LocalDateTime createDate;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetResponse{
        private Integer commentId;
        private String content;
        private String id;
        private String profileImageLink;
        private LocalDateTime createDate;
        private boolean is_update;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DelRequest{
        private Integer commentId;
        private Integer memberId;

    }

}
