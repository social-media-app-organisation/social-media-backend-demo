package com.example.socialmediabackenddemo.Model.Business;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String lien;
    private String type;
}
