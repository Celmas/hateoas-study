package ru.itis.hateoasstudy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String tittle;
    private String description;
    @OneToOne
    private User author;
    private Timestamp createdDate;

    @OneToMany
    private List<Comment> comments;
}
