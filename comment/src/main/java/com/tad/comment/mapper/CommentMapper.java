package com.tad.comment.mapper;

import com.tad.comment.dto.CommentRequest;
import com.tad.comment.model.Comment;

public class CommentMapper {
    public static Comment toComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setContent(commentRequest.content());
        comment.setUserId(commentRequest.userID());
        comment.setTaskName(commentRequest.taskName());
        comment.setProblemId(commentRequest.TaskID());

        return comment;
    };
}
