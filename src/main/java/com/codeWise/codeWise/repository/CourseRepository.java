package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
