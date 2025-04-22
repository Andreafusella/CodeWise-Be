package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
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
    private String role;


    public Teacher(String name, String lastName, String email, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}