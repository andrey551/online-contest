package com.tad.comment.dto;

import com.tad.comment.model.Comment;
import com.tad.comment.model.enums.TransactionStatus;

import java.util.List;
import java.util.Optional;

public record CommentsResponse(
        TransactionStatus status,
        Optional<List<Comment>> comments
) {}
