package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain=true)
public class User {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userId;//unique
    private String password;//hash
    private String nickname;
//    private String rank;//enum
//    private Point location;
    @OneToMany(mappedBy = "contributor")
    private List<DocumentVersion> contributedList = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public void contributeDocument(DocumentVersion version) {
        version.setContributor(this);
        this.contributedList.add(version);
    }

    public void writeComment(Comment comment) {
        comment.setUser(this);
        this.commentList.add(comment);
    }
}
