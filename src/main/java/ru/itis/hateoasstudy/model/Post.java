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

    private String title;
    private String description;
    @OneToOne
    private User author;
    private Timestamp createdDate;

    @OneToMany
    private List<Comment> comments;

    public void flushComments() {
        if (comments.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.comments.clear();
        }
    }
}
