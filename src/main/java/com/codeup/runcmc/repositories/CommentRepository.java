package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Comment;
import com.codeup.runcmc.models.Topster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByBody(String body);

    void deleteAllByTopster(Topster topster);
}
