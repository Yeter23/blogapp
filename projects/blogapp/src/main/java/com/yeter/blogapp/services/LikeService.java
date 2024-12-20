package com.yeter.blogapp.services;

import com.yeter.blogapp.controllers.LikeController;
import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Like;
import com.yeter.blogapp.entities.Post;
import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.LikeRepository;
import com.yeter.blogapp.requests.CommentCreateRequest;
import com.yeter.blogapp.requests.LikeCreateRequest;
import com.yeter.blogapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list= likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
           list= likeRepository.findByUserId(userId.get());
        } else if(postId.isPresent()){
            list= likeRepository.findByPostId(postId.get());
        } else
            list =likeRepository.findAll();
       return list.stream().map(like-> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {

        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }


    public void deleteOneByLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
