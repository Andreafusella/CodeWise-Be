package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewTeacherCourseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.model.TeacherCourse;
import com.codeWise.codeWise.repository.CourseRepository;
import com.codeWise.codeWise.repository.TeacherCourseRepository;
import com.codeWise.codeWise.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherCourseService {

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    public TeacherCourse create(NewTeacherCourseDto dto) {
        Optional<Teacher> teacher = teacherRepository.findById(dto.getIdTeacher());
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());

        if (teacher.isPresent() && course.isPresent()) {
            TeacherCourse teacherCourse = new TeacherCourse(
                    teacher.get(),
                    course.get()
            );
            return teacherCourseRepository.save(teacherCourse);
        }

        if (teacher.isEmpty()) {
            throw new EntityNotFoundException("Not exist Teacher with id: " + dto.getIdTeacher());
        } else {
            throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
        }
    }
}
