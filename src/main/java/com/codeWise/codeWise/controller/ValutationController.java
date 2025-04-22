package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewValuationDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Valuation;
import com.codeWise.codeWise.service.ValutationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valutation")
@Tag(name = "Valutation", description = "Endpoints for managing valuations")
public class ValutationController {

    @Autowired
    private ValutationService valutationService;

    @Operation(summary = "Create a new valuation")
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

    @Operation(summary = "Get all valuations")
    @GetMapping
    public ResponseEntity<List<Valuation>> getAll() {
        try {
            List<Valuation> valuations = valutationService.getAll();
            return ResponseEntity.ok(valuations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Get a valuation by ID")
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

    @Operation(summary = "Delete a valuation by ID")
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