package com.example.socialmediabackenddemo.Controlles;

import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events.LoginEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import com.example.socialmediabackenddemo.Model.Services.Common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private LoginEventHandler loginEventHandler;
    @Autowired
    UserService userService;
    @PostMapping({"/login"})
    ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) throws Exception {
        LoginResponse loginResponse = loginEventHandler.handle(loginCommand);
        return new ResponseEntity<>(loginResponse, loginResponse.getStatus());
    }
    @GetMapping({"/test"})
    ResponseEntity<String> test()  {
        userService.testttt();
        return new ResponseEntity<>("test succes", HttpStatus.OK);
    }
}
