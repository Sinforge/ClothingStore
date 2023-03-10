package ru.sinforge.clothingstore.Entities;

import jakarta.persistence.*;
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
    private User id_user;

    @OneToOne
    private Cloth id_cloth;

    private boolean is_paid;

    private int price;

}
