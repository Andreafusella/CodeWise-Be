package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {

    void deleteAllByTeacherId(Long teacherId);

    void deleteAllByCourseId(Long courseId);

    List<TeacherCourse> findAllByTeacherId(Long teacherId);

    List<TeacherCourse> findAllByCourseId(Long courseId);

}
