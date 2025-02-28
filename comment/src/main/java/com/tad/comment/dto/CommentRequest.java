package com.tad.comment.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CommentRequest(
   String taskName,
   String content,
   UUID userID,
   UUID TaskID,
   Timestamp createdAt
) {}
