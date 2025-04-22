package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewExerciseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Exercise;
import com.codeWise.codeWise.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
@Tag(name = "Exercise", description = "Endpoints for managing exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Operation(summary = "Create a new exercise")
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

    @Operation(summary = "Get all exercises")
    @GetMapping
    public ResponseEntity<List<Exercise>> getAll() {
        try {
            List<Exercise> exercises = exerciseService.getAll();
            return ResponseEntity.ok(exercises);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get an exercise by ID")
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

    @Operation(summary = "Delete an exercise by ID")
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