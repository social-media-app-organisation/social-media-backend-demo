package com.example.socialmediabackenddemo.Model.Services.Common;

import com.example.socialmediabackenddemo.Controlles.TestCommand;
import com.example.socialmediabackenddemo.Model.Business.Role;
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

    public void testttt(){
        User user = new User();
        user.setName("chaimae");
        user.setEmail("chaimaeboug2000@gmail.com");
        user.setUserMedia(null);
        user.setRole(null);
        user.setPassword(passwordEncoder.encode("chaimae2000"));
        saveUser(user);

    }
    public String testRoole(String name){
        Role role = new Role();
        role.setRoleName(name);
        role = roleRepository.save(role);
        return "success";
    }

}
