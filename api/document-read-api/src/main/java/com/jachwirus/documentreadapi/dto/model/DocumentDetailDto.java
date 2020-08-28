package com.jachwirus.documentreadapi.dto.model;

import com.jachwirus.documentreadapi.model.Comment;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain=true)
public class DocumentDetailDto{
    private Long id;
    private String title;
    private String thumbnailURL;
    private String contents;
    private int likes;
    private int dislikes;
    private int viewCount;
    private String category;
    private List<String> hashTags;
    private Date lastModified;
    private List<Comment> comments;
    private int flag;
}
