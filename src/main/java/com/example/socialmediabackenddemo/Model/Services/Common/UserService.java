package com.example.socialmediabackenddemo.Model.Services.Common;

import com.example.socialmediabackenddemo.Model.Business.User;
import com.example.socialmediabackenddemo.Model.Services.Database.RoleRepository;
import com.example.socialmediabackenddemo.Model.Services.Database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public boolean ExistEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public boolean ValidPassword(User user,String password){
        return passwordEncoder.matches(password,user.getPassword());
    }
}
