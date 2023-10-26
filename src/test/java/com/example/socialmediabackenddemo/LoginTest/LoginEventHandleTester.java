package com.example.socialmediabackenddemo.LoginTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Handlers.Events.LoginEventHandler;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses.LoginResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class LoginEventHandleTester extends GeneriqueTester {

    @Autowired
    LoginEventHandler eventHandler;


    @Test
    public void TestResponseNotNull() throws Exception {
        LoginCommand command = new LoginCommand("test@test.com","test");
       LoginResponse response = eventHandler.handle(command);
        Assert.notNull(response);
    }
    @Test
    public void testSuccessOfCorrectLogin() throws Exception {
        LoginCommand command = new LoginCommand("test@test.com","test");
        LoginResponse response = eventHandler.handle(command);
        Assert.notNull(response);
        Assert.isTrue(response.isSuccessful());
        Assert.notNull(response.getToken());

    }
    @Test
    public void testFailedOfInCorrectLogin() throws Exception {
        LoginCommand command = new LoginCommand("test@2test.com","test2test");
        LoginResponse response = eventHandler.handle(command);
        Assert.notNull(response);
        Assert.isTrue(!response.isSuccessful());
        Assert.isNull(response.getToken());
    }
    @Test
    public void testEmptyTokenWhenIncorrectLogin() throws Exception
    {
        LoginCommand command = new LoginCommand("test@2test.com","test2test");
        LoginResponse response = eventHandler.handle(command);
        Assert.notNull(response);
        Assert.isNull(response.getToken());
    }
    @Test
    public void testNotEmptyTokenWhenIncorrectLogin() throws Exception
    {
        LoginCommand command = new LoginCommand("test@test.com","test");
        LoginResponse response = eventHandler.handle(command);
        Assert.notNull(response);
        Assert.notNull(response.getToken());
    }


}
