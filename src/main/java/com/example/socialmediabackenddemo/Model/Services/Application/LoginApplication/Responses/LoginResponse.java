package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.Responses;

import com.example.socialmediabackenddemo.Common.Responses.BasicResponse;
import com.example.socialmediabackenddemo.Model.Business.User;
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
    public void setData(User sourceUser, String sourceToken){
        user =  new UserDTO(
                sourceUser.getId(),
                sourceUser.getName(),
                sourceUser.getBirthDay(),
                sourceUser.getUsername(),
                sourceUser.getEmail(),
                sourceUser.getPhone(),
                sourceUser.getGender(),
                sourceUser.getBio(),
                sourceUser.getRole().getRoleName()
        );
        token=sourceToken;
    }
}
