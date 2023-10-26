package com.example.socialmediabackenddemo.RegisterTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.VerificationEmailCommand;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationRequestControllerTest extends GeneriqueTester {
    @Test
    public void testRegistrationFieldWhenUserAlreadyExist() throws Exception {
        String token = getTokenFromRegisterCommand("test","test@test.com","test");
        VerificationEmailCommand command = new VerificationEmailCommand(token);
        mvc
                .perform(post(path+"/user/verify-email").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRegistrationFieldWhenInfosCorrect() throws Exception {
        String token = getTokenFromRegisterCommand("testName","testEmail@gmail.com","testPassword");
        VerificationEmailCommand command = new VerificationEmailCommand(token,"2000-10-20","testUsername","0615487899","FEMALE");
        mvc
                .perform(post(path+"/user/verify-email").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
