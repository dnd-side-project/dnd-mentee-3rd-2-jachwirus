package com.jachwirus.documentreadapi.dto.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain=true)
public class DocumentInfoDto {
    private Long id;
    private String title;
    private String thumbnailURL;
    private int likes;
    private int dislikes;
    private int viewCount;
    private String category;
    private List<String> hashTags;
    private int numberOfComments;
    private Date lastModified;
}
