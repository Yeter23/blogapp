package com.yeter.blogapp.services;

import com.yeter.blogapp.entities.User;
import com.yeter.blogapp.repositories.UserRepository;
import com.yeter.blogapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userRepository.findByUserName(username);


        return JwtUserDetails.create(user);
    }
    public UserDetails loadUserById(Long id){
        User user =userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
