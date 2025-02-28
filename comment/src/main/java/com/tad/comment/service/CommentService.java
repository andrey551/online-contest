package com.tad.comment.service;

import com.tad.comment.dto.CommentRequest;
import com.tad.comment.dto.CommentResponse;
import com.tad.comment.dto.CommentsResponse;
import com.tad.comment.mapper.CommentMapper;
import com.tad.comment.model.Comment;
import com.tad.comment.model.enums.TransactionStatus;
import com.tad.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentResponse addComment(CommentRequest commentRequest) {
        try {
            Comment commentToSave = CommentMapper.toComment(commentRequest);

            Comment createdComment = commentRepository.save(commentToSave);

            return new CommentResponse(TransactionStatus.SUCCESS, Optional.of(createdComment));
        } catch (Exception e) {
            return new CommentResponse(TransactionStatus.INTERNAL_ERROR, Optional.empty());
        }

    }


    public CommentsResponse getCommentsByTaskName(String taskName) {
        try {
            List<Comment> comments = commentRepository.findCommentsByTaskName(taskName).get();

            if(comments.isEmpty())
                return new CommentsResponse(TransactionStatus.NOT_FOUND, Optional.empty());

            return new CommentsResponse(TransactionStatus.SUCCESS, Optional.of(comments));
        } catch (Exception e) {
            return new CommentsResponse(TransactionStatus.INTERNAL_ERROR, Optional.empty());
        }

    }

    public Comment getComment(UUID id) {
        return commentRepository.getReferenceById(id);
    }

    public CommentResponse updateComment(CommentRequest comment) {
        try {
            Comment commentToUpdate = commentRepository.findCommentByUserIdAndCreatedAt(comment.userID(),
                    comment.createdAt());

            if (commentToUpdate == null) {
                return new CommentResponse(TransactionStatus.NOT_FOUND, Optional.empty());
            }

            commentToUpdate.setContent(comment.content());
            commentToUpdate.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            commentRepository.save(commentToUpdate);

            return new CommentResponse(TransactionStatus.SUCCESS, Optional.empty());
        } catch (Exception e) {
            return new CommentResponse(TransactionStatus.INTERNAL_ERROR, Optional.empty());
        }
    }

    public CommentResponse deleteComment(CommentRequest comment) {
        try {
            commentRepository.deleteCommentByUserIdAndCreatedAt(comment.userID(), comment.createdAt());

            return new CommentResponse(TransactionStatus.SUCCESS, Optional.empty());
        } catch (Exception e) {
            return new CommentResponse(TransactionStatus.INTERNAL_ERROR, Optional.empty());
        }
    }
}
