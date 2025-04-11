package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String subject;
    private String text;
    private LocalDate uploadDate;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "resources")
    private List<Student> students;


    public Resource(String code, String subject, String text, LocalDate uploadDate, Teacher teacher) {
        this.code = code;
        this.subject = subject;
        this.text = text;
        this.uploadDate = uploadDate;
        this.teacher = teacher;
    }

}
