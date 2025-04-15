package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.repository.CourseRepository;
import com.codeWise.codeWise.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public Course createCourse(NewCourseDto dto) {

        Course course = new Course(
                dto.getName(),
                dto.getAccademicYear(),
                dto.getCreditNumber(),
                dto.getDegreeProgram()
        );
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return course.get();
        }

        throw new EntityNotFoundException("Not exist Course with id: " + id);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public void deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            courseRepository.deleteById(id);
        }

        throw new EntityNotFoundException("Not exist Course with id: " + id);
    }
}