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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new course",
            description = "Creates a new course with its name, academic year, and credit number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "409", description = "Email already exists (Note: This exception type seems incorrect for course creation based on the DTO)",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get course by ID",
            description = "Retrieves a course's details based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "409", description = "Course not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get all courses",
            description = "Retrieves a list of all existing courses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of courses retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping()
    public ResponseEntity<?> getAllCourse() {
        try {
            List<Course> courses = courseService.getAll();
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete course by ID",
            description = "Deletes a course based on its unique ID and unlinks all associated students.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        try {
            courseService.deleteCourseById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get course and exercise information as Excel",
            description = "Generates and returns an Excel file (.xlsx) containing information about courses, associated exercises, and the number of enrolled students per course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excel file generated and returned",
                    content = @Content(mediaType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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