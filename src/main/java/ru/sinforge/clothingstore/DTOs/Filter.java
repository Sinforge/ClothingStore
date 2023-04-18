package ru.sinforge.clothingstore.DTOs;

import jakarta.persistence.ElementCollection;

import java.util.List;

public class Filter {
    public String name;
    public List<String> brandNames;
    public List<String> sections;
    public String gender;
    public List<String> colors;
    public List<String> sizes;
    public int minPrice = -1;
    public int maxPrice = -1;
}
