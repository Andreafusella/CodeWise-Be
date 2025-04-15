package com.codeWise.codeWise.service;

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

    
}