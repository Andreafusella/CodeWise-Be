package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.service.PaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paper")
@Tag(name = "Paper", description = "Endpoints for managing papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    // @Operation(summary = "Create a new paper")
    // @PostMapping
    
}
