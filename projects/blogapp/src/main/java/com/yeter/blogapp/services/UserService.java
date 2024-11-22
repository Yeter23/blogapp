package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.Comment;
import com.yeter.blogapp.entities.Like;
import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.CommentRepository;
import com.yeter.blogapp.repositories.LikeRepository;
import com.yeter.blogapp.repositories.PostRepository;
import com.yeter.blogapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository,LikeRepository likeRepository,
                       CommentRepository commentRepository,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository=likeRepository;
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser=user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());

            userRepository.save(foundUser);
            return foundUser;
        } else
            return null;
    }

    public void deleteById(Long userId) {

        userRepository.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    public List<Object> getUserActivity(Long userId) {
    List<Long>postIds = postRepository.findTopByUserId(userId);
    if (postIds.isEmpty())
        return null;
   List<Object> comments= commentRepository.findUserCommentsByPostId(postIds);
    List<Object> likes=likeRepository.findUserLikesByPostId(postIds);
    // bir liste yigib sonra onlari merge eledik
    List<Object> result= new ArrayList<>();
    result.addAll(comments);
    result.addAll(likes);
    return result;
    }
}
