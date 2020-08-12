package com.jachwirus.documentreadapi.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String dataURL;
    private int likes;
    private int dislikes;
    private String thumbnailURL;
    private long last_version_id;// int [ref: > DocumentVersion.id]
    private int view_count;
    private String category;

    public Document(
            String title, String dataURL, int likes, int dislikes, String thumbnailURL,
            long last_version_id, int view_count, String category){

            this.title = title;
            this.dataURL = dataURL;
            this.likes = likes;
            this.dislikes = dislikes;
            this.thumbnailURL = thumbnailURL;
            this.last_version_id = last_version_id;
            this.view_count = view_count;
            this.category = category;
    }
}