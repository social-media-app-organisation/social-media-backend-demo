package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses;

import com.example.socialmediabackenddemo.Common.Responses.BasicResponse;
import com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.DTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse extends BasicResponse {
    private String token;
    private UserDTO user;
}
