package ru.sinforge.clothingstore.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Idenify by unique email
    private String email;

    private String country;

    private String name;

    private String surname;

}
