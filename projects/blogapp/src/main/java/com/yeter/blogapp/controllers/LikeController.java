package com.yeter.blogapp.controllers;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Like;
import com.yeter.blogapp.requests.CommentCreateRequest;
import com.yeter.blogapp.requests.CommentUpdateRequest;
import com.yeter.blogapp.requests.LikeCreateRequest;
import com.yeter.blogapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId,postId);

    }
    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }



    @DeleteMapping("/{likeId}")

    public  void  deleteOnLike(@PathVariable Long likeId){
        likeService.deleteOneByLike(likeId);
    }
}
