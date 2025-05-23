package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByCourseId(Long id);

    void deleteAllByCourseId(Long id);
}
