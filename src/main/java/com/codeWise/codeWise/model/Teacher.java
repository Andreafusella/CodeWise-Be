package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @OneToMany(mappedBy = "teacher")
    private List<Resource> resources;

    @OneToMany(mappedBy = "teacher")
    private List<Exercise> exercises;

    @ManyToMany(mappedBy = "teachers")
    @JsonBackReference
    private List<Course> courses;
}