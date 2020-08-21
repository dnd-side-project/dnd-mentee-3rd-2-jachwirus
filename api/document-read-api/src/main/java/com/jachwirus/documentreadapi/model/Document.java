package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"versions", "comments", "tags"})
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain=true)
public class Document {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int likes;
    private int dislikes;
    private int viewCount;
    private String category;

    @OneToMany(mappedBy = "document")
    private List<DocumentVersion> versions = new ArrayList<>();
    @OneToMany(mappedBy = "document")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "document")
    private List<DocumentHashTag> tags = new ArrayList<>();

    public void addNewVersion(DocumentVersion version){
        version.setDocument(this);
        this.versions.add(version);
    }

    public void addHashTag(DocumentHashTag mapping, HashTag tag) {
        mapping.setDocument(this);
        mapping.setHashTag(tag);
        this.tags.add(mapping);
        tag.getDocuments().add(mapping);
    }
}