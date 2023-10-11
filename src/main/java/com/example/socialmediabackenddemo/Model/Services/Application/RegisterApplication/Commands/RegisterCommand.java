package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand {
    private String name;
    private String email;
    private String password;
}
