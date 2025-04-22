package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    public Course createCourse(NewCourseDto dto) {

        Course course = new Course(
                dto.getName(),
                dto.getAccademicYear(),
                dto.getCreditNumber()
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

    @Transactional
    public void deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            studentService.unsetCourseFromStudents(id);

            courseRepository.deleteById(id);
            return;
        }

        throw new EntityNotFoundException("Not exist Course with id: " + id);
    }
}