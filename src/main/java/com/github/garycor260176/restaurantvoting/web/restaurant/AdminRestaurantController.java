package com.github.garycor260176.restaurantvoting.web.restaurant;

import com.github.garycor260176.restaurantvoting.error.IllegalRequestDataException;
import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.to.RestaurantTo;
import com.github.garycor260176.restaurantvoting.util.RestaurantUtil;
import com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/admin/restaurants";

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

    @GetMapping("/{id}/history")
    public ResponseEntity<Restaurant> getWithDishesOnDate(@PathVariable int id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get restaurant {} with dishes on date {}", id, date);
        return ResponseEntity.of(Optional.of(repository.getWithDishes(id, date)
                .orElseThrow(() -> new IllegalRequestDataException("Not found at this date"))));
    }

    @GetMapping("/{id}/all-history")
    public ResponseEntity<Restaurant> getWithAllDishes(@PathVariable int id) {
        log.info("get restaurant {} with all dishes", id);
        return ResponseEntity.of(Optional.of(repository.getWithAllDishes(id)
                .orElseThrow(() -> new IllegalRequestDataException("Not found at this date"))));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        repository.deleteExisted(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        ValidationUtil.checkNew(restaurantTo);
        Restaurant created = repository.save(RestaurantUtil.createNewFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update {} with id{}", restaurantTo, id);
        assureIdConsistent(restaurantTo, id);
        Restaurant restaurant = repository.getExisted(id);
        repository.save(RestaurantUtil.updateFromTo(restaurant, restaurantTo));
    }


}
