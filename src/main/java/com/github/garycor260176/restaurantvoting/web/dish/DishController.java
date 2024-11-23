package com.github.garycor260176.restaurantvoting.web.dish;

import com.github.garycor260176.restaurantvoting.model.Dish;
import com.github.garycor260176.restaurantvoting.repository.DishRepository;
import com.github.garycor260176.restaurantvoting.repository.RestaurantRepository;
import com.github.garycor260176.restaurantvoting.to.DishTo;
import com.github.garycor260176.restaurantvoting.util.DishUtil;
import com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DishController {
    static final String REST_URL = "/api/admin/dishes";

    private final DishRepository repository;

    private final RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true)
    })
    @Transactional
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo, Integer restaurantId) {
        log.info("create {} in restaurant {}", dishTo, restaurantId);
        ValidationUtil.checkNew(dishTo);
        Dish created = DishUtil.createNewFromTo(dishTo);
        created.setRestaurant(restaurantRepository.getExisted(restaurantId));
        repository.save(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true)
    })
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id) {
        log.info("update {} with id {}", dishTo, id);
        assureIdConsistent(dishTo, id);
        Dish dish = repository.getExisted(id);
        repository.save(DishUtil.updateFromTo(dish, dishTo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "restaurants", allEntries = true)
    })
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
