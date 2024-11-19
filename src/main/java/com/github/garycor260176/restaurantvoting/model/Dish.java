package com.github.garycor260176.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonBackReference
    private Restaurant restaurant;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @NotNull
    @Column(name = "dish_date", nullable = false)
    private LocalDate dish_date;

    public Dish(Integer id, String name, LocalDate dish_date, BigDecimal price) {
        super(id, name);
        this.price = price;
        this.dish_date = dish_date;
    }

    public Dish(Dish dish) {
        this(dish.id, dish.name, dish.dish_date, dish.price);
    }

}
