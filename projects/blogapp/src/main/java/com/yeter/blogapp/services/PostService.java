package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.Like;
import com.yeter.blogapp.entities.Post;
import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.PostRepository;
import com.yeter.blogapp.requests.PostCreateRequest;
import com.yeter.blogapp.requests.PostUpdateRequest;
import com.yeter.blogapp.responses.LikeResponse;
import com.yeter.blogapp.responses.PostResponse;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
  private PostRepository postRepository;
  private UserService userService;
  private LikeService likeService;

    public PostService(PostRepository postRepository,UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;
    }

    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
//iki yolla yazmaq olar sqlle getirib join ede bilerik
        //yada javada kod olaraq yaza map ile
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }
        list = postRepository.findAll();
       return list.stream().map(p -> {
          List<LikeResponse> likes= likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of( p.getId()));
           return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

        public Post getOnePostById (Long postId){
            return postRepository.findById(postId).orElse(null);
        }

        public Post createOnePost (PostCreateRequest newPostRequest){
            User user = userService.getOneUserById(newPostRequest.getUserId());
            if (user == null)
                return null;
            Post toSave = new Post();
            toSave.setId(newPostRequest.getId());
            toSave.setText(newPostRequest.getText());
            toSave.setTitle(newPostRequest.getTitle());
            toSave.setUser(user);
            toSave.setCreateDate(new Date());
            return postRepository.save(toSave);
        }

        public Post updateOnePostById (Long postId, PostUpdateRequest updatePost){
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                Post toUpdate = post.get();
                toUpdate.setTitle(updatePost.getTitle());
                toUpdate.setText(updatePost.getText());
                postRepository.save(toUpdate);
                return toUpdate;
            }
            return null;
        }

        public void deleteOneByPost (Long postId){
            postRepository.deleteById(postId);
        }
    }
