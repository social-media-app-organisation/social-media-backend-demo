package com.example.socialmediabackenddemo.Model.Business;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String roleName;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "role")
    private Set<User> users = new HashSet<>();
}
