package com.codeWise.codeWise.model;

import com.codeWise.codeWise.type.RoleTeacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private RoleTeacher role;

    public Teacher(String name, String lastName, String email, RoleTeacher role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
