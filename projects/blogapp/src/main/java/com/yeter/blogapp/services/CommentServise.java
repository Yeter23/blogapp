package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Post;
import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.CommentRepository;
import com.yeter.blogapp.requests.CommentCreateRequest;
import com.yeter.blogapp.requests.CommentUpdateRequest;
import com.yeter.blogapp.responses.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServise {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentServise(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
       List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()){
        comments= commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            comments= commentRepository.findByUserId(userId.get());
        } else if(postId.isPresent()){
            comments= commentRepository.findByPostId(postId.get());
        } else
            comments= commentRepository.findAll();
return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }


    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user= userService.getOneUserById(request.getUserId());
        Post post= postService.getOnePostById(request.getPostId());
        if(user!=null && post!=null){
            Comment commentToSave= new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            commentToSave.setCreateDate(new Date());
            return commentRepository.save(commentToSave);
        }  else
            return null;
    }


    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment=commentRepository.findById(commentId);
        if(comment.isPresent()){
          Comment commentToUpdate=comment.get();
          commentToUpdate.setText(request.getText());
          return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneByComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
