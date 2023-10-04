package com.example.socialmediabackenddemo.model.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_media")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class UserMedia extends Media{
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}