package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
