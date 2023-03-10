package ru.sinforge.clothingstore.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="cloths")
@Getter
@Setter
public class Cloth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String brand_name;

    private String section;

    private Gender gender;

    private String color;

    @ElementCollection
    private List<String> sizes;

    private int price;




}
