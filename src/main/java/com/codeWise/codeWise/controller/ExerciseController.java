package com.codeWise.codeWise.controller;

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

    // @Operation(summary = "Create a new exercise")
    // @PostMapping
    
    
}