package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewPaperDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.service.PaperService;
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
@RequestMapping("/api/paper")
@Tag(name = "Paper", description = "Endpoints for managing papers (student submissions)")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Operation(summary = "Create a new paper submission",
            description = "Creates a new paper submission linked to an exercise and a student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paper submission created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paper.class))),
            @ApiResponse(responseCode = "404", description = "Exercise or Student not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewPaperDto dto) {
        try {
            Paper paper = paperService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(paper);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all paper submissions",
            description = "Retrieves a list of all paper submissions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of paper submissions retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<List<Paper>> getAll() {
        try {
            List<Paper> papers = paperService.getAll();
            return ResponseEntity.ok(papers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get a paper submission by ID",
            description = "Retrieves a specific paper submission based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paper submission found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Paper.class))),
            @ApiResponse(responseCode = "404", description = "Paper submission not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Paper paper = paperService.getById(id);
            return ResponseEntity.ok(paper);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a paper submission by ID",
            description = "Deletes a paper submission based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paper submission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Paper submission not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            paperService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}