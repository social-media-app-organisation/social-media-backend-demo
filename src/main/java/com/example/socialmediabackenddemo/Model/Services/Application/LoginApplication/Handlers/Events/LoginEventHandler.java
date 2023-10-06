package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events;

import com.example.socialmediabackenddemo.Common.Handlers.BasicHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.DTOs.UserDTO;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginEventHandler extends BasicHandler<LoginCommand , LoginResponse> {

    @Override
    public LoginResponse handle(LoginCommand c) throws Exception {
        LoginResponse loginResponse =new LoginResponse("tokken",new UserDTO());
        loginResponse.Success("Authentication successfully");
        return loginResponse;
    }
}
