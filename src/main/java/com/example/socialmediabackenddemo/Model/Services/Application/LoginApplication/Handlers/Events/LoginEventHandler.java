package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events;

import com.example.socialmediabackenddemo.Common.Handlers.BasicHandler;
import com.example.socialmediabackenddemo.Common.jwt_services.Service.JwtService;
import com.example.socialmediabackenddemo.Model.Business.User;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.DTOs.UserDTO;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import com.example.socialmediabackenddemo.Model.Services.Common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginEventHandler extends BasicHandler<LoginCommand , LoginResponse> {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @Override
    public LoginResponse handle(LoginCommand loginCommand) throws Exception {
        LoginResponse loginResponse = new LoginResponse();

        String email=loginCommand.getEmail();
        String password=loginCommand.getPassword();

        User user = userService.getUserByEmail(email);

        if(user != null) {
            if (userService.ValidPassword(user, password)) {
                String token = jwtService.createJwtToken(user);
                loginResponse.setData(user,token);
                loginResponse.Success("Authentication successfully");

            } else {
                loginResponse.setToken(null);
                loginResponse.Error("Password or email Incorrect");
            }

        }else{
            loginResponse.setToken(null);

            loginResponse.Error("Password or email Incorrect");
        }
        return loginResponse;
    }
}
