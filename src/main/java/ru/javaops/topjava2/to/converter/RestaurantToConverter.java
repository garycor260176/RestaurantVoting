package ru.javaops.topjava2.to.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;

@Component
public class RestaurantToConverter implements Converter<Restaurant, RestaurantTo> {
    @Override
    public RestaurantTo convert(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }
}
