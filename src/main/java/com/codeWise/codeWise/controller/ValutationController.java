package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewValuationDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Valuation;
import com.codeWise.codeWise.service.ValutationService;
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
@RequestMapping("/api/valutation")
@Tag(name = "Valutation", description = "Endpoints for managing valuations of papers")
public class ValutationController {

    @Autowired
    private ValutationService valutationService;

    @Operation(summary = "Create a new valuation",
            description = "Creates a new valuation for a specific paper submission.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Valuation created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Valuation.class))),
            @ApiResponse(responseCode = "404", description = "Paper not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewValuationDto dto) {
        try {
            Valuation valuation = valutationService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(valuation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all valuations",
            description = "Retrieves a list of all existing valuations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of valuations retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<List<Valuation>> getAll() {
        try {
            List<Valuation> valuations = valutationService.getAll();
            return ResponseEntity.ok(valuations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get a valuation by ID",
            description = "Retrieves a specific valuation based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valuation found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Valuation.class))),
            @ApiResponse(responseCode = "404", description = "Valuation not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Valuation valuation = valutationService.getById(id);
            return ResponseEntity.ok(valuation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a valuation by ID",
            description = "Deletes a valuation based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Valuation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Valuation not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            valutationService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}