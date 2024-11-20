package com.github.garycor260176.restaurantvoting.web.restaurant;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@CacheConfig(cacheNames = "restaurants")
public class AbstractRestaurantController {
    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get restaurant {} without dishes", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants without dishes");
        return repository.findAll(Sort.by("name").ascending().and(Sort.by("address").ascending()));
    }

    public ResponseEntity<Restaurant> getWithCurrentDishes(int id) {
        log.info("get restaurant {} with current dishes", id);
        return ResponseEntity.of(repository.getWithDishesBetween(id, LocalDate.now(), LocalDate.now()));
    }

    public List<Restaurant> getAllWithCurrentDishes() {
        log.info("get restaurants with current dishes");
        return repository.getAllWithDishesOnDate(LocalDate.now());
    }

    public List<Restaurant> findByNameAndAddress(String name, String address) {
        log.info("search restaurant without dishes by name = {} and address = {}", name, address);
        return repository.findByNameAndAddressIgnoreCase(name != null ? name : "", address != null ? address : "");
    }
}
