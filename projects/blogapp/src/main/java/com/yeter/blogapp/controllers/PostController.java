package com.yeter.blogapp.controllers;

import com.yeter.blogapp.entities.Post;
import com.yeter.blogapp.requests.PostCreateRequest;
import com.yeter.blogapp.requests.PostUpdateRequest;
import com.yeter.blogapp.responses.PostResponse;
import com.yeter.blogapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
  private   PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
       return postService.getAllPosts(userId);

    }
    @GetMapping("/{postId}")
    public PostResponse getOnePost(@PathVariable Long postId){
        return postService.getOnePostByIdWithLikes(postId);
    }
    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }
    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId,updatePost);
    }
    @DeleteMapping("/{postId}")
    public  void  deleteOnPost(@PathVariable Long postId){
        postService.deleteOneByPost(postId);
    }
}
