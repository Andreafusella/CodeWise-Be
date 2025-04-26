package com.codeWise.codeWise.controller;

import com.codeWise.codeWise.dto.request.NewNoteDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Note;
import com.codeWise.codeWise.service.NoteService;
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
@RequestMapping("/api/note")
@Tag(name = "Note", description = "Endpoints for managing notes linked to students and attachments")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Operation(summary = "Create a new note",
            description = "Creates a new note linked to a student and an attachment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Note.class))),
            @ApiResponse(responseCode = "404", description = "Student or Attachment not found",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Get all notes",
            description = "Retrieves a list of all existing notes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of notes retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
    @GetMapping
    public ResponseEntity<?> getAllNotes() {
        try {
            List<Note> notes = noteService.getAll();
            return ResponseEntity.ok().body(notes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Get a note by ID",
            description = "Retrieves a specific note based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Note.class))),
            @ApiResponse(responseCode = "404", description = "Note not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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

    @Operation(summary = "Delete a note by ID",
            description = "Deletes a note based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note deleted successfully",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Note not found with the given ID",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "text/plain"))
    })
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