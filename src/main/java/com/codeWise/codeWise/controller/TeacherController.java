package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewTeacherDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@Tag(name = "Teacher", description = "Endpoints for managing teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Operation(summary = "Create a new teacher",
            description = "Creates a new teacher with the provided details. Returns the created teacher object on success.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody NewTeacherDto newTeacherDto) {
        try {
            Teacher teacher = teacherService.createTeacher(newTeacherDto);
            return ResponseEntity.ok().body(teacher);
        } catch (EmailExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get teacher by ID",
            description = "Retrieves a teacher's details based on their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "409", description = "Teacher not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        try {
            Teacher teacher = teacherService.getById(id);
            return ResponseEntity.ok().body(teacher);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all teachers",
            description = "Retrieves a list of all registered teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of teachers retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherService.getAll();
            return ResponseEntity.ok().body(teachers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete teacher by ID",
            description = "Deletes a teacher based on their unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted successfully"),
            @ApiResponse(responseCode = "409", description = "Teacher not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        try {
            teacherService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get teachers as CSV",
            description = "Generates and returns a CSV file containing the data of all registered teachers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CSV file generated and returned",
                    content = @Content(mediaType = "text/csv")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/csv")
    public ResponseEntity<?> getCsvTeachers() {
        try {
            return teacherService.getCsv();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}