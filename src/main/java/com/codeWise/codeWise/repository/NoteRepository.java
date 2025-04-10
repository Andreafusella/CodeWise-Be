package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
