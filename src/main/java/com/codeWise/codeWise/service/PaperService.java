package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewPaperDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.Exercise;
import com.codeWise.codeWise.model.Paper;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.ExerciseRepository;
import com.codeWise.codeWise.repository.PaperRepository;
import com.codeWise.codeWise.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Paper create(NewPaperDto dto) {
        Optional<Exercise> exercise = exerciseRepository.findById(dto.getIdExercise());
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        if (exercise.isPresent() && student.isPresent()) {
            Paper paper = new Paper(
                    dto.getComment(),
                    dto.getUploadDate(),
                    dto.getFileUrl(),
                    exercise.get(),
                    student.get()
            );
            return paperRepository.save(paper);
        }

        if (!exercise.isPresent()) {
            throw new EntityNotFoundException("Not found exercise with id: " + dto.getIdExercise());
        }
        throw new EntityNotFoundException("Not found Student with id: " + dto.getIdStudent());
    }

    public List<Paper> getAll() {
        return paperRepository.findAll();
    }

    public Paper getById(Long id) {
        Optional<Paper> paper = paperRepository.findById(id);

        if (paper.isPresent()) {
            return paper.get();
        }
        throw new EntityNotFoundException("Not found Paper with id: " + id);
    }

    public void deleteById(Long id) {
        Optional<Paper> paper = paperRepository.findById(id);

        if (paper.isPresent()) {
            paperRepository.delete(paper.get());
            return;
        }
        throw new EntityNotFoundException("Not found Paper with id: " + id);
    }

    
}