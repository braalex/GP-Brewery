package com.braalex.brewery.entity;

import com.braalex.brewery.dto.IngredientType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "ingredient")
@Entity
public class IngredientEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    private String name;
    @ManyToMany
    @JoinTable(name = "beer_ingredient",
            joinColumns = @JoinColumn(name = "beer_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
            )
    private List<BeerEntity> beers = new ArrayList<>();
}
