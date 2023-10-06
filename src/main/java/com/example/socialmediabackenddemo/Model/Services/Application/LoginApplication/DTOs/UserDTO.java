package com.example.socialmediabackenddemo.Model.Services.Application.LoginApplication.DTOs;

import com.example.socialmediabackenddemo.Model.Business.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String name;
    private LocalDate birthDay;
    private String username;
    private String email;
    private String phone;
    private Gender gender;
    private String password;
    private String bio;
    private String role;
}
