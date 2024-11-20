package com.github.garycor260176.restaurantvoting.util;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestaurantUtil {
    public Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), restaurantTo.getAddress());
    }

    public Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        restaurant.setAddress(restaurantTo.getAddress());
        return restaurant;
    }
}