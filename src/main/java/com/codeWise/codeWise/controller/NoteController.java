package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.model.Note;
import com.codeWise.codeWise.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@Tag(name = "Note", description = "Endpoints for managing notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // @Operation(summary = "Create a new note")
    // @PostMapping
    
}