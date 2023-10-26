package com.example.socialmediabackenddemo.RegisterTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.EmailSentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class VerificationEmailEventHandlerTest extends GeneriqueTester {

    @Test
    public void TestVerificationEmailTokenShouldNotBeNull(){
        String token = getTokenFromRegisterCommand("testName","testEmail@gmail.com","testPassword");
        Assert.assertNotNull(token);
    }
    @Test
    public void TestVerificationEmailTokenShouldNotBeEmpty(){
        String token = getTokenFromRegisterCommand("testName","testEmail@gmail.com","testPassword");
        Assert.assertFalse(token.isEmpty());
    }
    @Test
    public void TestVerificationFieldWithEmailAlreadyExist(){
        RegisterCommand registerCommand = new RegisterCommand("testName","test@test.com","testPassword");
        EmailSentResponse response = verificationEmailEventHandler.handle(registerCommand);
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
    }

}
