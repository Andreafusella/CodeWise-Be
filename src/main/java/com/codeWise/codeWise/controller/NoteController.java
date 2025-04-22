package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewNoteDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
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

    @Operation(summary = "Create a new note")
    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody NewNoteDto dto) {
        try {
            Note note = noteService.createNote(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(note);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get all notes")
    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        try {
            List<Note> notes = noteService.getAll();
            return ResponseEntity.ok().body(notes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get a note by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        try {
            Note note = noteService.getById(id);
            return ResponseEntity.ok().body(note);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a note by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        try {
            noteService.deleteNoteById(id);
            return ResponseEntity.ok().body("Note with ID " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}