package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewStudentResourceDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.StudentResource;
import com.codeWise.codeWise.service.StudentResourceService;
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
@RequestMapping("/api/resource-student")
@Tag(name = "Resource-Student", description = "Endpoints for managing student-resource associations")
public class StudentResourceController {

    @Autowired
    private StudentResourceService studentResourceService;

    @Operation(summary = "Create a new student-resource association",
            description = "Creates a new association between a student and a resource.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student-resource association created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResource.class))),
            @ApiResponse(responseCode = "409", description = "Student or Resource not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping()
    public ResponseEntity<?> createStudentResource(@RequestBody NewStudentResourceDto dto) {
        try {
            StudentResource studentResource = studentResourceService.create(dto);
            return ResponseEntity.ok().body(studentResource);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get student-resource association by ID",
            description = "Retrieves a specific student-resource association based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student-resource association found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResource.class))),
            @ApiResponse(responseCode = "409", description = "Student-resource association not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentResourceById(@PathVariable Long id) {
        try {
            StudentResource studentResource = studentResourceService.getById(id);
            return ResponseEntity.ok().body(studentResource);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all student-resource associations",
            description = "Retrieves a list of all existing student-resource associations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of student-resource associations retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping()
    public ResponseEntity<?> getAllStudent() {
        try {
            List<StudentResource> studentResource = studentResourceService.getAll();
            return ResponseEntity.ok().body(studentResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete student-resource association by ID",
            description = "Deletes a student-resource association based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student-resource association deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResourceById(@PathVariable Long id) {
        try {
            studentResourceService.deleteById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}