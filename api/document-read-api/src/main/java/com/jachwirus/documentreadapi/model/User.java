package com.jachwirus.documentreadapi.model;

import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userId;//unique
    private String password;//hash
    private String nickname;
//    private String rank;//enum
//    private Point location;
    @OneToMany(mappedBy = "contributor")
    private List<DocumentVersion> contributedList;
}
