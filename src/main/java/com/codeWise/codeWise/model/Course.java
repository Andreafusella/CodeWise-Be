package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @ManyToMany
    @JsonManagedReference
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "course")
    private List<Attachment> attachments;

    @OneToMany(mappedBy = "course")
    private List<UniversitaryProject> universitaryProjects;
}