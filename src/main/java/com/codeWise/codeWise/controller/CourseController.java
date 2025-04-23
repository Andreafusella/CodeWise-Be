package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Tag(name = "Course", description = "Endpoints for managing courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Operation(summary = "Create a new course")
    @PostMapping()
    public ResponseEntity<?> createCourse(@RequestBody NewCourseDto newCourseDto) {
        try {
            Course course = courseService.createCourse(newCourseDto);
            return ResponseEntity.ok().body(course);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get course by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok().body(course);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all course")
    @GetMapping()
    public ResponseEntity<?> getAllCourse() {
        try {
            List<Course> courses = courseService.getAll();
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete course by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get Excel file with info Course Exam")
    @GetMapping("/excel")
    public ResponseEntity<ByteArrayResource> getExcelFile() {
        try {
            ByteArrayResource resource = courseService.getExcelFile();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=courses.xlsx")
                    .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}