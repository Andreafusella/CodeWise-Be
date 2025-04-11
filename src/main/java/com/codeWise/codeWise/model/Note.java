package com.codeWise.codeWise.model;

import com.codeWise.codeWise.type.CategoryNote;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private LocalDate uploadDate;
    private CategoryNote category;

    @ManyToOne
    private Attachments attachments;

    @ManyToOne
    private Student student;

    public Note(String title, String description, LocalDate uploadDate, CategoryNote category) {
        this.title = title;
        this.description = description;
        this.uploadDate = uploadDate;
        this.category = category;
    }
}
