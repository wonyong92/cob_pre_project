package com.team23.PreProject.comment.mapper;

import com.team23.PreProject.comment.dto.comment_dto;
import com.team23.PreProject.comment.entity.comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface comment_mapper {
    default comment_dto.PostResponse commentToResponse(comment comment){
        return comment_dto.PostResponse
                .builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
//                .profileImageLink(comment.getMember().getProfile().getProfileImageLink())
                .id(comment.getMember().getId())
                .build();

    }
    default List<comment_dto.GetResponse> commentsToResponse(List<comment> comments){
        return comments.stream()
                .map(comment -> comment_dto.GetResponse
                        .builder()
                        .commentId(comment.getCommentId())
                        .content(comment.getContent())
                        .createDate(comment.getCreateDate())
                        .id(comment.getMember().getId())
                        .is_update(comment.getIs_update())
//                        .profileImageLink(comment.getMember().getProfile().getProfileImageLink())
                        .build())
                .collect(Collectors.toList());

    }
}