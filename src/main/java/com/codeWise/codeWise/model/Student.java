package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @ManyToMany
    private List<Resource> readResources;

    @OneToMany(mappedBy = "student")
    private List<Paper> papers;

    @OneToMany(mappedBy = "student")
    private List<Note> notes;

    @ManyToMany
    private List<UniversitaryProject> universitaryProjects;
}