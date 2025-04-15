package com.codeWise.codeWise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TeacherCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Course course;

    public TeacherCourse(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }
}
