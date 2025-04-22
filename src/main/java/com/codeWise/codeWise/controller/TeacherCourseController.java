package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.dto.request.NewTeacherCourseDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.TeacherCourse;
import com.codeWise.codeWise.service.TeacherCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-course")
@Tag(name = "Teacher Course", description = "Endpoints for managing Teacher Course")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Operation(summary = "Create a new teacher-course")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody NewTeacherCourseDto newCourseDto) {
        try {
            TeacherCourse teacherCourse = teacherCourseService.create(newCourseDto);
            return ResponseEntity.ok().body(teacherCourse);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get teacher-course by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            TeacherCourse teacherCourse = teacherCourseService.getById(id);
            return ResponseEntity.ok().body(teacherCourse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all teacher-course")
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            List<TeacherCourse> teacherCourse = teacherCourseService.getAll();
            return ResponseEntity.ok().body(teacherCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete teacher-course by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            teacherCourseService.deleteById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
