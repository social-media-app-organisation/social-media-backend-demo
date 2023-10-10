package com.example.socialmediabackenddemo.Model.Services.Application.RegisterApplication.Commands;

import com.example.socialmediabackenddemo.Model.Business.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEmailCommand {
    private String token;
    private LocalDate birthDate;
    private String username;
    private String phone;
    private String gender;
}
