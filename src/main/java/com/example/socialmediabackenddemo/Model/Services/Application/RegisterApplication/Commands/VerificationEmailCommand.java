package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEmailCommand {
    private String token;
    private String birthDate;
    private String username;
    private String phone;
    private String gender;

    public VerificationEmailCommand(String token) {
        this.token = token;
    }
}
