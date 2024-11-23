package com.yeter.blogapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super();
    }
    // bele de olar
    public UserNotFoundException(String message){
        super(message);
    }


}






