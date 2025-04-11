package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
