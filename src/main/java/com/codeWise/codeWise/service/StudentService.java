package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.dto.request.SetStudentToCourseDto;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public Student createStudent(NewStudentDto newStudentDto) {
        System.out.println("Student ricevuto: " + newStudentDto);
        Optional<Student> existingStudent = studentRepository.findByEmail(newStudentDto.getEmail());

        if (existingStudent.isPresent()) {
            throw new EmailExistException("Email Already Exist");
        }

        Student student = new Student(
                newStudentDto.getName(),
                newStudentDto.getLastName(),
                newStudentDto.getEmail(),
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
            studentRepository.delete(student.get());
            return;
        }

        throw new EntityNotFoundException("Not exist Student with id: " + id);
    }

    public void unsetCourseFromStudents(Long courseId) {
        List<Student> students = studentRepository.findAllByCourseId(courseId);
        for (Student student : students) {
            student.setCourse(null);
        }
        studentRepository.saveAll(students);
    }

    public void setCourse(SetStudentToCourseDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());
        if (student.isPresent() && course.isPresent()) {
            student.get().setCourse(course.get());
            studentRepository.save(student.get());
            return;
        }

        if (!student.isPresent()) {
            throw new EntityNotFoundException("Not exist Student with id: " + dto.getIdStudent());
        }
        throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
    }
}