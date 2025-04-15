package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.StudentRepository;
import com.codeWise.codeWise.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public Student createStudent(NewStudentDto newStudentDto) {
        Optional<Student> existingStudent = studentRepository.findByEmail(newStudentDto.getEmail());

        if (existingStudent.isPresent()) {
            throw new EmailExistException("Email Already Exist");
        }

        Student student = new Student(
                newStudentDto.getName(),
                newStudentDto.getLastName(),
                newStudentDto.getLastName(),
                newStudentDto.getDateBirth(),
                newStudentDto.getPlaceBirth(),
                newStudentDto.getYearRegistration()
        );

        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return student.get();
        }

        throw new EntityNotFoundException("Not exist Student with id: " + id);
    }

    public List<Student> getAll() {
        List<Student> list = studentRepository.findAll();
        return list;
    }

    public void deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()){
            studentRepository.deleteById(id);
        }

        throw new EntityNotFoundException("Not exist Student with id: " + id);
    }
}