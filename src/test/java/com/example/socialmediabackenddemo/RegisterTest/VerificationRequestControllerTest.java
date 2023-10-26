package com.example.socialmediabackenddemo.RegisterTest;

import com.example.socialmediabackenddemo.GeneriqueTester;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands.RegisterCommand;
import com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Handlers.Events.VerificationEmailEventHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VerificationRequestControllerTest extends GeneriqueTester {
    @Autowired
    VerificationEmailEventHandler verificationEmailEventHandler;

    @Test
    public void testVerificationEmailWithUserAlreadyExistShouldReturnBAD_REQUEST() throws Exception {
        RegisterCommand command = new RegisterCommand("test","test@test.com","test");
        mvc
                .perform(post(path+"/user/register").content(gson.toJson(command))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
