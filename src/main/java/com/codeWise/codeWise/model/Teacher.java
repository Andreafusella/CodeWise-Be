package com.codeWise.codeWise.model;

import com.codeWise.codeWise.type.RoleTeacher;
import jakarta.persistence.*;
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
    private RoleTeacher type;

    public Teacher(String name, String lastName, RoleTeacher type, String email) {
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
    }
}
