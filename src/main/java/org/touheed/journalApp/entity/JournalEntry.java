package org.touheed.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "JOURNAL")
@Getter
@Setter
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Title_Name",length = 150)
    private String title;

    @Lob
    private String content;

    private LocalDateTime date;

    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;
}
