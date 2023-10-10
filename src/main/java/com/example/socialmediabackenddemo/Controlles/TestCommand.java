package com.example.socialmediabackenddemo.Controlles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TestCommand {
    String name;
    String email;
    String password;
}
