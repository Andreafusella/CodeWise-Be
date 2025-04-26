package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.dto.request.NewTeacherCourseDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.TeacherCourse;
import com.codeWise.codeWise.service.TeacherCourseService;
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
@RequestMapping("/api/teacher-course")
@Tag(name = "Teacher Course", description = "Endpoints for managing teacher-course associations")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Operation(summary = "Create a new teacher-course association",
            description = "Creates a new association between a teacher and a course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher-course association created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherCourse.class))),
            @ApiResponse(responseCode = "409", description = "Teacher or Course not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody NewTeacherCourseDto newCourseDto) {
        try {
            TeacherCourse teacherCourse = teacherCourseService.create(newCourseDto);
            return ResponseEntity.ok().body(teacherCourse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get teacher-course association by ID",
            description = "Retrieves a specific teacher-course association based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher-course association found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherCourse.class))),
            @ApiResponse(responseCode = "409", description = "Teacher-course association not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get all teacher-course associations",
            description = "Retrieves a list of all existing teacher-course associations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of teacher-course associations retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try {
            List<TeacherCourse> teacherCourse = teacherCourseService.getAll();
            return ResponseEntity.ok().body(teacherCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete teacher-course association by ID",
            description = "Deletes a teacher-course association based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher-course association deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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