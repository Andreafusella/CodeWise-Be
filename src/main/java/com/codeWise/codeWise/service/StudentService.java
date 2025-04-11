package com.codeWise.codeWise.service;

import com.codeWise.codeWise.exception.NotExistResourceException;
import com.codeWise.codeWise.exception.NotExistStudentException;
import com.codeWise.codeWise.model.Resource;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.ResourceRepository;
import com.codeWise.codeWise.repository.StudentRepository;
import com.codeWise.codeWise.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotExistStudentException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setDateBirth(studentDetails.getDateBirth());
        student.setPlaceBirth(studentDetails.getPlaceBirth());
        student.setYearRegistration(studentDetails.getYearRegistration());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    public void addResourceToStudent(Long studentId, Long resourceId) {
        Student student = getStudentById(studentId);
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new NotExistResourceException("Resource not found with id: " + resourceId));

        student.getResources().add(resource);
        studentRepository.save(student);
    }

    public void removeResourceFromStudent(Long studentId, Long resourceId) {
        Student student = getStudentById(studentId);
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new NotExistResourceException("Resource not found with id: " + resourceId));

        student.getResources().remove(resource);
        studentRepository.save(student);
    }
}

