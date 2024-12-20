package com.yeter.blogapp.responses;

import com.yeter.blogapp.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String userName;
    public  UserResponse(User entity){
        this.id=entity.getId();
        this.userName=entity.getUserName();
    }
}
