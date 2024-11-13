package com.github.garycor260176.restaurantvoting.web.restaurant;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/restaurants";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}/with-current-dishes")
    public ResponseEntity<Restaurant> getWithCurrentDishes(@PathVariable int id) {
        return super.getWithCurrentDishes(id);
    }

    @Override
    @GetMapping("/with-current-dishes")
    public List<Restaurant> getAllWithCurrentDishes() {
        return super.getAllWithCurrentDishes();
    }
}
