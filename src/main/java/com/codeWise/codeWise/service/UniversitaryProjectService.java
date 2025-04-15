package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.ResourceNotFoundException;
import com.codeWise.codeWise.model.UniversitaryProject;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.UniversitaryProjectRepository;
import com.codeWise.codeWise.repository.CourseRepository;
import com.codeWise.codeWise.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversitaryProjectService {

    @Autowired
    private UniversitaryProjectRepository universitaryProjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    
}