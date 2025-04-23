package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.dto.request.SetStudentToCourseDto;
import com.codeWise.codeWise.exception.AlreadySetCourseStudentException;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.exception.NotAlreadySetCourseStudentException;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@Tag(name = "Student", description = "Endpoints for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Create a new student")
    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody NewStudentDto newStudent) {
        try {
            Student student = studentService.createStudent(newStudent);
            return ResponseEntity.ok().body(student);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Set course for student")
    @PostMapping("/set-course")
    public ResponseEntity<?> setCourseStudent(@RequestBody SetStudentToCourseDto dto) {
        try {
            studentService.setCourse(dto);
            return ResponseEntity.ok().body("Student " + dto.getIdStudent() + " set to course: " + dto.getIdCourse());
        } catch (AlreadySetCourseStudentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Unset course for student")
    @PostMapping("/unset-course")
    public ResponseEntity<?> unsetCourseStudent(@RequestBody SetStudentToCourseDto dto) {
        try {
            studentService.unsetCourse(dto);
            return ResponseEntity.ok().body("Student " + dto.getIdStudent() + " unset to course: " + dto.getIdCourse());
        } catch (NotAlreadySetCourseStudentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get student by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getById(id);
            return ResponseEntity.ok().body(student);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all student")
    @GetMapping()
    public ResponseEntity<?> getAllStudent() {
        try {
            List<Student> students = studentService.getAll();
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete student by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get student info PDF")
    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> getStudentPdf(@PathVariable Long id) {
        try {
            return studentService.getStudentInfoPDF(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}