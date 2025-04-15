package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.NotExistStudentException;
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

    @Operation(summary = "Get student by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getById(id);
            return ResponseEntity.ok().body(student);
        } catch (NotExistStudentException e) {
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
}