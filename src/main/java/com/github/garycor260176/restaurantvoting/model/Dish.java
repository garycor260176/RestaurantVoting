package com.github.garycor260176.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_date", "name"}, name = "dish_unique_restaurant_date_name_idx")})
@Getter
@Setter
@NoArgsConstructor
public class Dish extends NamedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @NotNull
    @Column(name = "dish_date", nullable = false)
    private LocalDate date;

    public Dish(Integer id, String name, LocalDate date, BigDecimal price) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public Dish(Dish dish) {
        this(dish.id, dish.name, dish.date, dish.price);
    }

}
