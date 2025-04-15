package com.codeWise.codeWise.model;

import com.codeWise.codeWise.type.RoleTeacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NonNull;

@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    @NonNull
    @Email
    private String email;

    @NonNull
    private RoleTeacher role;


    public Teacher(String name, String lastName, String email, RoleTeacher role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}