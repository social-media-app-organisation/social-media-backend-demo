package com.example.socialmediabackenddemo.Model.Services.Database;

import com.example.socialmediabackenddemo.Model.Business.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User findUserById(long Id);
}
