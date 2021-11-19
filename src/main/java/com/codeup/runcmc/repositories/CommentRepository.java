package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByBody(String body);
}
