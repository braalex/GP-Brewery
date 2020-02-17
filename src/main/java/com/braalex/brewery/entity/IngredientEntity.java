package com.braalex.brewery.entity;

import com.braalex.brewery.dto.IngredientType;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "ingredient")
@Entity
public class IngredientEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    private String name;
}
