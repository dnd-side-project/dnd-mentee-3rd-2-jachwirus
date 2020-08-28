package com.jachwirus.documentreadapi.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain=true)
@Table(indexes = {@Index(name = "hash_tag_index", columnList = "tag_name", unique = true)})
public class HashTag {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "hashTag")
    private List<DocumentHashTag> documents = new ArrayList<>();

    @Column(name="tag_name")
    private String tagName;
}
