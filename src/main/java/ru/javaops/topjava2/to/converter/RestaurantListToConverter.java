package ru.javaops.topjava2.to.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantListToConverter implements Converter<List<Restaurant>, List<RestaurantTo>> {

    private final Converter<Restaurant, RestaurantTo> restaurantToConverter;

    @Override
    public List<RestaurantTo> convert(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(restaurantToConverter::convert)
                .toList();
    }
}
