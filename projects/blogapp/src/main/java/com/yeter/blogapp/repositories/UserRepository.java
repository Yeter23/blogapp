package com.yeter.blogapp.repositories;

import com.yeter.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
// repository bizim database istek atmagimizin metodlu halidi deye bilerik
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
