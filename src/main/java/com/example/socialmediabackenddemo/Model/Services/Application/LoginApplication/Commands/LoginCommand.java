package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginCommand {
    private String email;
    private String password;
}
