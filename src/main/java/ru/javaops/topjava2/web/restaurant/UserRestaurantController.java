package ru.javaops.topjava2.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.Restaurant;

import java.util.List;

@RestController
@Slf4j
@CacheConfig(cacheNames = "restaurants")
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/restaurant";

    @Override
    @GetMapping("/{id}/with-lunches")
    public ResponseEntity<Restaurant> getWithCurrentLunches(@PathVariable int id) {
        return super.getWithCurrentLunches(id);
    }

    @Override
    @GetMapping("/all-with-lunches")
    @Cacheable
    public List<Restaurant> getAllWithCurrentLunches() {
        return super.getAllWithCurrentLunches();
    }
}
