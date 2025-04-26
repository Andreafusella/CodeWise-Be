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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/api/student")
@Tag(name = "Student", description = "Endpoints for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Create a new student",
            description = "Registers a new student with the provided personal details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody NewStudentDto newStudent) {
        try {
            Student student = studentService.createStudent(newStudent);
            log.info("Save Student id: " + student.getId());
            return ResponseEntity.ok().body(student);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.warning("Generated Exception in createStudent: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Set course for a student",
            description = "Assigns a student to a specific course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course set successfully for the student",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Student or Course not found, or student already enrolled in a course",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/set-course")
    public ResponseEntity<?> setCourseStudent(@RequestBody SetStudentToCourseDto dto) {
        try {
            studentService.setCourse(dto);
            log.info("Set Course: " + dto.getIdCourse() + " to Student id: " + dto.getIdStudent());
            return ResponseEntity.ok().body("Student " + dto.getIdStudent() + " set to course: " + dto.getIdCourse());
        } catch (AlreadySetCourseStudentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.warning("Generated Exception in setCourseStudent: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Unset course for a student",
            description = "Removes a student's association with a course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course unset successfully for the student",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Student or Course not found, or student not enrolled in any course",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/unset-course")
    public ResponseEntity<?> unsetCourseStudent(@RequestBody SetStudentToCourseDto dto) {
        try {
            studentService.unsetCourse(dto);
            log.info("Unset Course: " + dto.getIdCourse() + " to Student id: " + dto.getIdStudent());
            return ResponseEntity.ok().body("Student " + dto.getIdStudent() + " unset to course: " + dto.getIdCourse());
        } catch (NotAlreadySetCourseStudentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.warning("Generated Exception in unsetCourseStudent: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get student by ID",
            description = "Retrieves a student's details based on their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "409", description = "Student not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getById(id);
            log.info("Get Student id: " + student.getId());
            return ResponseEntity.ok().body(student);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.warning("Generated Exception in getStudentById: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all students",
            description = "Retrieves a list of all registered students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of students retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping()
    public ResponseEntity<?> getAllStudent() {
        try {
            List<Student> students = studentService.getAll();
            log.info("Get all Student");
            return ResponseEntity.ok().body(students);
        } catch (Exception e) {
            log.warning("Generated Exception in getAllStudent: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete student by ID",
            description = "Deletes a student based on their unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            log.info("Delete Student id: " + id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            log.warning("Generated Exception in deleteStudentById: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get student info as PDF",
            description = "Generates and returns a PDF file containing the detailed information of a student, including their enrolled course if any.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF file generated and returned",
                    content = @Content(mediaType = "application/pdf")),
            @ApiResponse(responseCode = "409", description = "Student not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> getStudentPdf(@PathVariable Long id) {
        try {
            return studentService.getStudentInfoPDF(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.warning("Generated Exception in getStudentPdf: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}