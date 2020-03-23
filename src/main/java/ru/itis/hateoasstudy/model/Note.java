package ru.itis.hateoasstudy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User author;
    private String title;
    private Timestamp notificationTime;
    @Enumerated(EnumType.STRING)
    private NoteStatus status;

    public void post() {
        if (this.status.equals(NoteStatus.Deleted)) {
            throw new IllegalArgumentException();
        } else {
            this.status = NoteStatus.Posted;
        }
    }
}
