package com.tad.comment.controller;

import com.tad.comment.dto.CommentRequest;
import com.tad.comment.dto.CommentResponse;
import com.tad.comment.dto.CommentsResponse;
import com.tad.comment.model.Comment;
import com.tad.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/task/{taskName}")
    public ResponseEntity<List<Comment>> getCommentsByTask(@PathVariable String taskName) {
        CommentsResponse commentsResponse = commentService.getCommentsByTaskName(taskName);

        return ResponseEntity
                .status(commentsResponse.status().getCode())
                .body(commentsResponse.comments().get());
    }

    @PostMapping(path = "/")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest comment) {
        CommentResponse returnResponse = commentService.addComment(comment);


        return ResponseEntity
                .status(returnResponse.status().getCode())
                .body(returnResponse.comment().get());
    }

    @PutMapping(path = "/")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentRequest comment) {
        CommentResponse returnResponse = commentService.updateComment(comment);

        return ResponseEntity
                .status(returnResponse.status().getCode())
                .body(returnResponse.comment().get());
    }

    @DeleteMapping()
    public ResponseEntity deleteComment(@RequestBody CommentRequest comment) {
        CommentResponse returnResponse = commentService.deleteComment(comment);

        return ResponseEntity
                .status(returnResponse.status().getCode())
                .body(returnResponse.comment().get());
    }
}
