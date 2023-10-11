package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.socialmediabackenddemo.Common.Handlers.BasicHandler;
import com.example.socialmediabackenddemo.Common.email_Services.EmailSender;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.EmailSentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class VerificationEmailEventHandler extends BasicHandler<RegisterCommand, EmailSentResponse> {
    @Value("${serverSecretKey}")
    private String SECRET_KEY;

    @Autowired
    EmailSender emailService;

    @Override
    public EmailSentResponse handle(RegisterCommand registerCommand) {
        EmailSentResponse emailSentResponse = new EmailSentResponse();
        if(registerCommand != null){
            emailService.SendVerificationEmail(generateToken(registerCommand),registerCommand.getEmail());
            emailSentResponse.Success("email sent successfully");
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
        return JWT.create().withPayload(payloadClaims).sign(algorithm);
    }

}
