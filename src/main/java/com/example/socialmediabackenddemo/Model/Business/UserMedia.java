package com.example.socialmediabackenddemo.Model.Business;

import javax.persistence.*;
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