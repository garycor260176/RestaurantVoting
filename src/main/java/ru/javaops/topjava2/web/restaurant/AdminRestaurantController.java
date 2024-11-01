package ru.javaops.topjava2.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.util.RestaurantUtil;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "restaurants")
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    private UniqueNameAddressValidator nameValidator;

    public AdminRestaurantController(RestaurantRepository repository, UniqueNameAddressValidator nameValidator) {
        this.repository = repository;
        this.nameValidator = nameValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(nameValidator);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        log.info("get{}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create{}", restaurantTo);
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

    @GetMapping("/{id}/history")
    @Transactional
    public ResponseEntity<Restaurant> getWithLunchesOnAnyDate(@PathVariable int id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getWithDishesWithSomeDate for rest{} and date{}", id, date);
        Optional<Restaurant> list = repository.getWithLunchesOnDate(id, date);
        if (list.isEmpty()) {
            throw new IllegalRequestDataException("Not found at this date");
        }
        return ResponseEntity.of(list);
    }

    @Override
    @GetMapping("/{id}/with-lunches")
    @Transactional
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