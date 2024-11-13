package com.github.garycor260176.restaurantvoting.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo {
    @NotNull
    LocalDate dish_date;

    @NotNull
    BigDecimal price;

    public DishTo(Integer id, String name, LocalDate dish_date, BigDecimal price) {
        super(id, name);
        this.dish_date = dish_date;
        this.price = price;
    }
}
