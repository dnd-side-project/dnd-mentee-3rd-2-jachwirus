package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;


import javax.persistence.*;
import java.util.*;

@Entity
@Data
@ToString(exclude = {"latestVersion", "versions", "comments", "tags"})
@Accessors(chain=true)
public class Document {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int likes;
    private int dislikes;
    private int viewCount;
    private String category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "latest_version_id")
    private DocumentVersion latestVersion;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private List<DocumentVersion> versions = new ArrayList<>();

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private List<DocumentHashTag> tags = new ArrayList<>();

    public void addNewVersion(DocumentVersion version){
        version.setDocument(this);
        this.setLatestVersion(version);
        this.versions.add(version);
    }

    public void addHashTag(DocumentHashTag mapping, HashTag tag) {
        mapping.setDocument(this);
        mapping.setHashTag(tag);
        this.tags.add(mapping);
        tag.getDocuments().add(mapping);
    }

    public void addComment(Comment comment) {
        comment.setDocument(this);
        comments.add(comment);
    }
}
