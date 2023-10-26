package com.example.socialmediabackenddemo.LoginTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands.LoginCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.VerificationEmailCommand;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTester extends GeneriqueTester {

    @Test
    public void testLoginWhenUserExistWithCorrectInformation() throws Exception {
        //User exist => test passed
        LoginCommand command = new LoginCommand(
                "test@test.com",
                "test"
        );
        mvc.perform(post(path+"/user/login").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testLoginWhenUserExistWithIncorrectEmail() throws Exception {
        //user exist but wrong email => test passed
        LoginCommand command = new LoginCommand(
                "test@test123.com",
                "test"
        );
        mvc
                .perform(post(path+"/user/login").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testLoginWhenUserExistWithIncorrectPassword() throws Exception {
        //user exist but wrong password => test passed
        LoginCommand command = new LoginCommand(
                "test@test.com",
                "admin"
        );
        mvc
                .perform(post(path+"/user/login").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testLoginWhenUserNotExist() throws Exception {
        //user not exist => test passed
        LoginCommand command = new LoginCommand(
                "test.com",
                "test-test"
        );
        mvc
                .perform(post(path+"/user/login").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
