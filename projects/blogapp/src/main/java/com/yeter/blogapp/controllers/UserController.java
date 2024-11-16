package com.yeter.blogapp.controllers;

import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//ana path adi users olacaq

public class UserController {
    //reponu tanimlayiriq
    private UserRepository userRepository;
    //constructor injection edek
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    // butun userleri getiren controller yazaq
    @GetMapping
    public List<User> getAllUsers(){
       return userRepository.findAll();
    }
    //yeni bir user create etmek ucun contrller yazaq
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    // tekce bir useri getiren metod yazaq
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //eger hemin user yoxdursa custom excp lazimdi
       return userRepository.findById(userId).orElse(null) ;
    }
    // deyisiklik update elemek ucun metod
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser ){
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
    // useri silmek ucun metod
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
    }
}
