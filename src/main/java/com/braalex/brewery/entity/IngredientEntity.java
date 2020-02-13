package com.braalex.brewery.entity;

import com.braalex.brewery.dto.IngredientType;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "ingredient")
@Entity
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private IngredientType type;

    @Column(name = "name")
    private String name;
}
