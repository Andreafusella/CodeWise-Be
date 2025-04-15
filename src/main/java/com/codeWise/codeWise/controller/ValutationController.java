package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Valutation;
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
@Tag(name = "Valutation", description = "Endpoints for managing valutations")
public class ValutationController {

    @Autowired
    private ValutationService valutationService;

    // @Operation(summary = "Create a new valutation")
    // @PostMapping
    
}