package com.codeWise.codeWise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String degreeProgram;
    private String name;
    private Integer accademicYear;
    private Integer creditNumber;

    public Course(String code, String degreeProgram, String name, Integer accademicYear, Integer creditNumber) {
        this.code = code;
        this.degreeProgram = degreeProgram;
        this.name = name;
        this.accademicYear = accademicYear;
        this.creditNumber = creditNumber;
    }
}
