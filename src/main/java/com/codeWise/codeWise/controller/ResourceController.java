package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewResourceDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.service.ResourceService;
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
@RequestMapping("/api/resource")
@Tag(name = "Resource", description = "Endpoints for managing resources (learning materials)")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Operation(summary = "Create a new resource",
            description = "Creates a new learning resource linked to a teacher.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "409", description = "Teacher not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping()
    public ResponseEntity<?> createResource(@RequestBody NewResourceDto newResourceDto) {
        try {
            Resource resource = resourceService.newResource(newResourceDto);
            return ResponseEntity.ok().body(resource);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get resource by ID",
            description = "Retrieves a specific resource based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "409", description = "Resource not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getResourceById(@PathVariable Long id) {
        try {
            Resource resource = resourceService.getById(id);
            return ResponseEntity.ok().body(resource);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all resources",
            description = "Retrieves a list of all existing resources.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of resources retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping()
    public ResponseEntity<?> getAllStudent() {
        try {
            List<Resource> resources = resourceService.getAll();
            return ResponseEntity.ok().body(resources);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete resource by ID",
            description = "Deletes a resource based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResourceById(@PathVariable Long id) {
        try {
            resourceService.deleteResourceById(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}