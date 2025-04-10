package com.codeWise.codeWise.service;

import com.codeWise.codeWise.model.TeacherCourse;
import com.codeWise.codeWise.repository.TeacherCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherCourseService {

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    public TeacherCourse createTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseRepository.save(teacherCourse);
    }

    public Optional<TeacherCourse> getTeacherCourseById(Long id) {
        return teacherCourseRepository.findById(id);
    }

    public List<TeacherCourse> getAllTeacherCourses() {
        return teacherCourseRepository.findAll();
    }

    public boolean deleteTeacherCourse(Long id) {
        if (teacherCourseRepository.existsById(id)) {
            teacherCourseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
