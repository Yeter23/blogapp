package com.yeter.blogapp.controllers;

import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.UserRepository;
import com.yeter.blogapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//ana path adi users olacaq

public class UserController {
    //reponu tanimlayiriq
    private UserService userService;
    //constructor injection edek
    public UserController(UserService userService){

        this.userService=userService;
    }
    // butun userleri getiren controller yazaq
    @GetMapping
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }
    //yeni bir user create etmek ucun contrller yazaq
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }
    // tekce bir useri getiren metod yazaq
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //eger hemin user yoxdursa custom excp lazimdi
       return userService.getOneUserById(userId) ;
    }
    // deyisiklik update elemek ucun metod
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser ){
     return userService.updateOneUser(userId,newUser);
    }
    // useri silmek ucun metod
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){

        userService.deleteById(userId);
    }
}
