package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewExerciseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Exercise;
import com.codeWise.codeWise.service.ExerciseService;
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
@RequestMapping("/api/exercise")
@Tag(name = "Exercise", description = "Endpoints for managing exercises/exams")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Operation(summary = "Create a new exercise",
            description = "Creates a new exercise or exam linked to a teacher and a course.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exercise created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Exercise.class))),
            @ApiResponse(responseCode = "404", description = "Course or Teacher not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewExerciseDto dto) {
        try {
            Exercise exercise = exerciseService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(exercise);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all exercises",
            description = "Retrieves a list of all existing exercises.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exercises retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<List<Exercise>> getAll() {
        try {
            List<Exercise> exercises = exerciseService.getAll();
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get an exercise by ID",
            description = "Retrieves a specific exercise based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Exercise.class))),
            @ApiResponse(responseCode = "404", description = "Exercise not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Exercise exercise = exerciseService.getById(id);
            return ResponseEntity.ok(exercise);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete an exercise by ID",
            description = "Deletes an exercise based on its unique ID. Returns a 204 No Content on successful deletion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Exercise not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            exerciseService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}