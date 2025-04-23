package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewCourseDto;
import com.codeWise.codeWise.dto.response.ExcelResponseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import java.io.ByteArrayOutputStream;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    public Course createCourse(NewCourseDto dto) {

        Course course = new Course(
                dto.getName(),
                dto.getAccademicYear(),
                dto.getCreditNumber()
        );
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return course.get();
        }

        throw new EntityNotFoundException("Not exist Course with id: " + id);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Transactional
    public void deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            studentService.unsetCourseFromStudents(id);

            courseRepository.deleteById(id);
            return;
        }

        throw new EntityNotFoundException("Not exist Course with id: " + id);
    }

    public ByteArrayResource getExcelFile() {
        List<ExcelResponseDto> list = courseRepository.findCourseWithExercises();
    
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Courses");

            Row header = sheet.createRow(0);
            String[] columns = {
                "Course Name", "Academic Year", "Credits",
                "Exercise Start", "Exercise End", "Description", "Tot Student"
            };
            for (int i = 0; i < columns.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowIdx = 1;
            for (ExcelResponseDto dto : list) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(dto.getNameCourse());
                row.createCell(1).setCellValue(dto.getAccademicYear());
                row.createCell(2).setCellValue(dto.getCreditNumber());
                row.createCell(3).setCellValue(dto.getDateStartExercise() != null ? dto.getDateStartExercise().toString() : "N/A");
                row.createCell(4).setCellValue(dto.getDateEndExercise() != null ? dto.getDateEndExercise().toString() : "N/A");
                row.createCell(5).setCellValue(dto.getDescriptionExercise() != null ? dto.getDescriptionExercise() : "No Description");
                row.createCell(6).setCellValue(dto.getNumberOfStudents());
            }
    
            workbook.write(out);
            return new ByteArrayResource(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate Excel file", e);
        }
    }
}