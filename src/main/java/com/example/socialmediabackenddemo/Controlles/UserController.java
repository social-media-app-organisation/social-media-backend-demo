package com.example.socialmediabackenddemo.Controlles;

import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events.LoginEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private LoginEventHandler loginEventHandler;

    @PostMapping({"/login"})
    ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) throws Exception {
        LoginResponse loginResponse = loginEventHandler.handle(loginCommand);
        return new ResponseEntity<>(loginResponse,loginResponse.getStatus());
    }
}
