package com.yeter.blogapp.controllers;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.requests.CommentCreateRequest;
import com.yeter.blogapp.requests.CommentUpdateRequest;
import com.yeter.blogapp.responses.CommentResponse;
import com.yeter.blogapp.services.CommentServise;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")

public class CommentControlller {
    private CommentServise commentServise;

    public CommentControlller(CommentServise commentServise) {
        this.commentServise = commentServise;
    }
    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentServise.getAllCommentsWithParam(userId,postId);

    }
    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentServise.getOneCommentById(commentId);
    }
    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request){
        return commentServise.createOneComment(request);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request ) {
        return commentServise.updateOneCommentById(commentId, request);

    }
    @DeleteMapping("/{commentId}")

    public  void  deleteOnComment(@PathVariable Long commentId){
        commentServise.deleteOneByComment(commentId);
    }
}
