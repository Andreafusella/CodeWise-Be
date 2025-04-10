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
    private Integer accademicYear;
    private String name;
    private Integer creditNumber;
    private String degreeProgram;

    public Course(String code, Integer accademicYear, String name, Integer creditNumber, String degreeProgram) {
        this.code = code;
        this.accademicYear = accademicYear;
        this.name = name;
        this.creditNumber = creditNumber;
        this.degreeProgram = degreeProgram;
    }
}
