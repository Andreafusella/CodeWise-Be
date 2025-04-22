package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewNoteDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Attachment;
import com.codeWise.codeWise.model.Note;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.AttachmentRepository;
import com.codeWise.codeWise.repository.NoteRepository;
import com.codeWise.codeWise.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Note createNote(NewNoteDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        Optional<Attachment> attachment = attachmentRepository.findById(dto.getIdAttachment());

        if (student.isPresent() && attachment.isPresent()) {
            Note note = new Note(
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getUploadDate(),
                    attachment.get(),
                    student.get()
            );

            return noteRepository.save(note);
        }

        if (!student.isPresent()) {
            throw new EntityNotFoundException("Not exist Student with id: " + dto.getIdStudent());
        }
        throw new EntityNotFoundException("Not exist Attachment with id: " + dto.getIdAttachment());
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note getById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            return note.get();
        }
        throw new EntityNotFoundException("Not exist Note with id: " + id);
    }

    public void deleteNoteById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            noteRepository.delete(note.get());
            return;
        }

        throw new EntityNotFoundException("Not exist Note with id: " + id);
    }
    
}