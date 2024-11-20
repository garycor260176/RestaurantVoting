package com.github.garycor260176.restaurantvoting.util;

import com.github.garycor260176.restaurantvoting.model.Dish;
import com.github.garycor260176.restaurantvoting.to.DishTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DishUtil {
    public Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), dishTo.getDate(), dishTo.getPrice());
    }

    public Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setDate(dishTo.getDate());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }
}
