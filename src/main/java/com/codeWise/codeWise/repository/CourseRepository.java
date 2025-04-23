package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.dto.response.ExcelResponseDto;
import com.codeWise.codeWise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
//    @Query("""
//    SELECT new com.codeWise.codeWise.dto.response.ExcelResponseDto(
//        c.name,
//        c.accademicYear,
//        c.creditNumber,
//        e.dateStart,
//        e.dateEnd,
//        e.description
//    )
//    FROM Course c
//    LEFT JOIN Exercise e ON e.course.id = c.id
//""")
//    List<ExcelResponseDto> findCourseWithExercises();

    @Query("""
    SELECT new com.codeWise.codeWise.dto.response.ExcelResponseDto(
        c.name,
        c.accademicYear,
        c.creditNumber,
        e.dateStart,
        e.dateEnd,
        e.description,
        COUNT(s.id)
    )
    FROM Course c
    LEFT JOIN Exercise e ON e.course.id = c.id
    LEFT JOIN Student s ON s.course.id = c.id
    GROUP BY c.id, c.name, c.accademicYear, c.creditNumber, e.dateStart, e.dateEnd, e.description
""")
    List<ExcelResponseDto> findCourseWithExercises();

}
