package com.jachwirus.documentreadapi.dto.mapper;

import com.jachwirus.documentreadapi.dto.model.CommentDto;
import com.jachwirus.documentreadapi.model.Comment;
import com.jachwirus.documentreadapi.model.User;

import java.util.Date;
import java.util.Optional;

public class CommentDtoMapper {
    public static CommentDto toCommentDto(Comment comment) {
        long id = Optional.ofNullable(comment.getId()).orElse(-1L);
        String contents = Optional.ofNullable(comment.getContents()).orElse("UNKNOWN");
        Date createdAt = Optional.ofNullable(comment.getCreatedAt()).orElse(null);
        boolean isModified = Optional.ofNullable(comment.isModified()).orElse(false);
        String user = Optional.ofNullable(comment.getUser())
                .orElse(new User().setNickname("UNKNOWN"))
                .getNickname();

        CommentDto dto = new CommentDto()
                .setId(id)
                .setContents(contents)
                .setCreatedAt(createdAt)
                .setModified(isModified)
                .setUserNickName(user);
        return dto;
    }
}
