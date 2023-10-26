package com.example.socialmediabackenddemo.RegisterTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.VerificationEmailCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events.RegistrationEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Responses.RegisterResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class RegistrationEventHandlerTest extends GeneriqueTester {
    @Autowired
    RegistrationEventHandler registrationEventHandler;

    @Test
    public void testResponseRegistrationNotNull(){
        String token = getTokenFromRegisterCommand("testName","testEmail@gmail.com","testPassword");
        VerificationEmailCommand command = new VerificationEmailCommand(token,"2000-10-20","testUsername","0615487899","FEMALE");
        RegisterResponse response = registrationEventHandler.handle(command);
        Assert.assertNotNull(response);
    }
    @Test
    public void testRegistrationSuccessfulWhenCorrectInfos(){
        String token = getTokenFromRegisterCommand("testName","testEmail@gmail.com","testPassword");
        VerificationEmailCommand command = new VerificationEmailCommand(token,"2000-10-20","testUsername","0615487899","FEMALE");
        RegisterResponse response = registrationEventHandler.handle(command);
        Assert.assertTrue(response.isSuccessful());
    }
    @Test
    public void testRegistrationFieldWhenEmailAlreadyExist(){
        String token = getTokenFromRegisterCommand("testName","test@test.com","testPassword");
        VerificationEmailCommand command = new VerificationEmailCommand(token);
        RegisterResponse response = registrationEventHandler.handle(command);
        Assert.assertFalse(response.isSuccessful());
    }
    @Test
    public void testRegistrationSuccessfulWhenTokenNull(){
        VerificationEmailCommand command = new VerificationEmailCommand();
        RegisterResponse response = registrationEventHandler.handle(command);
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void testRegistrationSuccessfulWhenTokenInvalid(){
        VerificationEmailCommand command = new VerificationEmailCommand("Adsadqwhuiohdioqhiochwoiqheocnqwondkoasnaodnask");
        RegisterResponse response = registrationEventHandler.handle(command);
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
    }
}
