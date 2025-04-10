package com.codeWise.codeWise.repository;

import com.codeWise.codeWise.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
