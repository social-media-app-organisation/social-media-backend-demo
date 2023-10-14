package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.socialmediabackenddemo.Common.Handlers.BasicHandler;
import com.example.socialmediabackenddemo.Common.email_Services.EmailSender;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.EmailSentResponse;
import com.example.socialmediabackenddemo.Model.Services.Common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class VerificationEmailEventHandler extends BasicHandler<RegisterCommand, EmailSentResponse> {
    @Value("${serverSecretKey}")
    private String SECRET_KEY;
    @Value("${tokenValidity}")
    public int TOKEN_VALIDITY;
    @Autowired
    EmailSender emailService;
    @Autowired
    UserService userService;

    @Override
    public EmailSentResponse handle(RegisterCommand registerCommand) {
        EmailSentResponse emailSentResponse = new EmailSentResponse();
        if(registerCommand != null){
            String email = registerCommand.getEmail();
            if(userService.ExistEmail(email)){ //if email already exist
                emailSentResponse.Error("this email is already exist, please try another email !");
            }else{
                String token = generateToken(registerCommand);
                emailService.SendVerificationEmail(token,email);
                emailSentResponse.Success("email sent successfully");
            }
        }else{
            emailSentResponse.Error("error on sending email");
        }
        return emailSentResponse;
    }

    public String generateToken(RegisterCommand user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Map<String, Object> payloadClaims = new HashMap<>();
        payloadClaims.put("name", user.getName());
        payloadClaims.put("password", user.getPassword());
        payloadClaims.put("email", user.getEmail());
        payloadClaims.put("action", "verifyEmail");
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() +  TOKEN_VALIDITY * 1000L))
                .withPayload(payloadClaims)
                .sign(algorithm);
    }

}
