package ru.sinforge.clothingstore.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;


    @Getter
    @Setter
    private Long clothid;

    @Getter
    @Setter
    private Long userid;

    @Getter
    @Setter
    private String text;

}
