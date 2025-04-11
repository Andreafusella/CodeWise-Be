package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;
    private String attachments;
    private LocalDate uploadDate;

    @ManyToOne
    private Student student;

    public Paper(String comment, String attachments, LocalDate uploadDate) {
        this.comment = comment;
        this.attachments = attachments;
        this.uploadDate = uploadDate;
    }
}
