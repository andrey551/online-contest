package com.tad.comment.dto;

import com.tad.comment.model.Comment;
import com.tad.comment.model.enums.TransactionStatus;

import java.util.Optional;

public record CommentResponse(
        TransactionStatus status,
        Optional<Comment> comment
) {}
