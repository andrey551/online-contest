package com.tad.comment.repository;

import com.tad.comment.dto.CommentRequest;
import com.tad.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<List<Comment>> findCommentsByTaskName(String taskName);

    Comment findCommentByUserIdAndCreatedAt(UUID userId, Timestamp createdAt);

    void deleteCommentByUserIdAndCreatedAt(UUID userId, Timestamp createdAt);
}
