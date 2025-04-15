package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
