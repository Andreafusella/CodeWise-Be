package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewStudentDto;
import com.codeWise.codeWise.dto.request.SetStudentToCourseDto;
import com.codeWise.codeWise.exception.AlreadySetCourseStudentException;
import com.codeWise.codeWise.exception.EmailExistException;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.exception.NotAlreadySetCourseStudentException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Student;
import com.codeWise.codeWise.repository.*;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public Student createStudent(NewStudentDto newStudentDto) {
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

    public void unsetCourse(SetStudentToCourseDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());
        if (student.isPresent() && course.isPresent()) {
            if (student.get().getCourse() != null) {
                student.get().setCourse(null);
                studentRepository.save(student.get());
                return;
            }
            throw new NotAlreadySetCourseStudentException("The student with id: " + dto.getIdStudent() + " is not registered in any course");
        }

        if (!student.isPresent()) {
            throw new EntityNotFoundException("Not exist Student with id: " + dto.getIdStudent());
        }
        throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
    }

    public void setCourse(SetStudentToCourseDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getIdStudent());
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());
        if (student.isPresent() && course.isPresent()) {
            if (student.get().getCourse() == null) {
                student.get().setCourse(course.get());
                studentRepository.save(student.get());
                return;
            }
            throw new AlreadySetCourseStudentException("The student with id: " + dto.getIdStudent() + " is already registered in a course");
        }

        if (!student.isPresent()) {
            throw new EntityNotFoundException("Not exist Student with id: " + dto.getIdStudent());
        }
        throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
    }

    public ResponseEntity<ByteArrayResource> getStudentInfoPDF(Long id) {
        Student student = getById(id);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("STUDENT INFORMATION")
                    .setBold()
                    .setFontSize(18));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("General Details").setBold().setFontSize(14));
            document.add(new Paragraph("Name: " + student.getName()));
            document.add(new Paragraph("Last Name: " + student.getLastName()));
            document.add(new Paragraph("Email: " + student.getEmail()));
            document.add(new Paragraph("Date of Birth: " + student.getDateBirth()));
            document.add(new Paragraph("Place of Birth: " + student.getPlaceBirth()));
            document.add(new Paragraph("Year of Registration: " + student.getYearRegistration()));

            if (student.getCourse() != null) {
                Course course = student.getCourse();

                document.add(new Paragraph(" "));
                document.add(new Paragraph("Enrolled Course").setBold().setFontSize(14));
                document.add(new Paragraph("Name: " + course.getName()));
                document.add(new Paragraph("Academic Year: " + course.getAccademicYear()));
                document.add(new Paragraph("Credits: " + course.getCreditNumber()));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Generated by CodeWise System").setFontSize(9));

            document.close();

            ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=student_" + id + ".pdf")
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}