package ru.sinforge.clothingstore.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="basket_cloth")
@Getter
@Setter
public class BasketCloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    private User user;


    @OneToOne
    private Cloth cloth;


    private String color;

    private String size;
}
