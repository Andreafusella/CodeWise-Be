package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByStudentId(Long id);

    List<Note> findAllByAttachmentId(Long id);

    void deleteAllByAttachmentId(Long id);

    void deleteAllByStudentId(Long id);
}
