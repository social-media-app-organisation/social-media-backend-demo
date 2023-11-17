package com.example.socialmediabackenddemo.Controlles;

import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events.LoginEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.VerificationEmailCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events.RegistrationEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events.VerificationEmailEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.EmailSentResponse;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.RegisterResponse;
import com.example.socialmediabackenddemo.Model.Services.Common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private LoginEventHandler loginEventHandler;
    @Autowired
    private VerificationEmailEventHandler verificationEmailEventHandler;
    @Autowired
    private RegistrationEventHandler registrationEventHandler;
    @Autowired
    UserService userService;
    @PostMapping({"/login"})
    ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) throws Exception {
        LoginResponse loginResponse = loginEventHandler.handle(loginCommand);
        return new ResponseEntity<>(loginResponse, loginResponse.getStatus());
    }

    @PostMapping({"/register"})
    ResponseEntity<EmailSentResponse> register(@RequestBody RegisterCommand command)  {
        EmailSentResponse emailSentResponse = verificationEmailEventHandler.handle(command);
        return new ResponseEntity<>(emailSentResponse, emailSentResponse.getStatus());
    }

    @PostMapping({"/verify-email"})
    ResponseEntity<RegisterResponse> verifyEmail(@RequestBody VerificationEmailCommand command) {
        RegisterResponse response = registrationEventHandler.handle(command);
        return new ResponseEntity<>(response, response.getStatus());
    }
    @PostMapping("/test-akeneo")
    ResponseEntity<String> testAkeneo(@RequestBody String str){
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
