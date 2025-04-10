package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate uploadDate;
    private String text;
    private String subject;
    private String code;

    @ManyToOne
    private Teacher teacher;

    public Resource(LocalDate uploadDate, String text, String subject, String code) {
        this.uploadDate = uploadDate;
        this.text = text;
        this.subject = subject;
        this.code = code;
    }
}
