package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Attachment;
import com.codeWise.codeWise.model.Note;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.AttachmentRepository;
import com.codeWise.codeWise.repository.NoteRepository;
import com.codeWise.codeWise.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    
}