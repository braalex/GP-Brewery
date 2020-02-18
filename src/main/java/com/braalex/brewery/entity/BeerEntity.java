package com.braalex.brewery.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "beer")
@Entity
public class BeerEntity extends BaseEntity {
    private String type;
    private String beerName;
    private Double abv;
    private Double originalGravity;
    private String description;
    @ManyToMany(mappedBy = "beers")
    private List<IngredientEntity> ingredients = new ArrayList<>();
    private Double price;
}
