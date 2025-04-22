package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.StudentResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentResourceRepository extends JpaRepository<StudentResource, Long> {

    void deleteAllByStudentId(Long studentId);

    void deleteAllByResourceId(Long resourceId);

    List<StudentResource> findAllByStudentId(Long studentId);

    List<StudentResource> findAllByResourceId(Long resourceId);
}
