package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewStudentResourceDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.StudentResource;
import com.codeWise.codeWise.service.StudentResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource-student")
@Tag(name = "Resource-Student", description = "Endpoints for managing resources-student")
public class StudentResourceController {

    @Autowired
    private StudentResourceService studentResourceService;

    @Operation(summary = "Create a new student-resource")
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

    @Operation(summary = "Get student-resource by id")
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

    @Operation(summary = "Get all student-resource")
    @GetMapping()
    public ResponseEntity<?> getAllStudent() {
        try {
            List<StudentResource> studentResource = studentResourceService.getAll();
            return ResponseEntity.ok().body(studentResource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete resource by id")
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
