package ru.javaops.topjava2.web.lunch;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.repository.LunchRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.LunchTo;
import ru.javaops.topjava2.util.LunchUtil;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = LunchController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LunchController {
    static final String REST_URL = "/api/admin/lunches";

    private final LunchRepository repository;

    private final RestaurantRepository restaurantRepository;

    public LunchController(LunchRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lunch> get(@PathVariable int id) {
        log.info("get{}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping
    public List<Lunch> getAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lunch> createWithLocation(@Valid @RequestBody LunchTo lunchTo, Integer restaurantId) {
        log.info("create{}", lunchTo);
        ValidationUtil.checkNew(lunchTo);
        Lunch created = LunchUtil.createNewFromTo(lunchTo);
        created.setRestaurant(restaurantRepository.findById(restaurantId).get());
        repository.save(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody LunchTo lunchTo, @PathVariable int id, Integer restaurantId) {
        log.info("update {} with id {}", lunchTo, id);
        ValidationUtil.assureIdConsistent(lunchTo, id);
        Lunch lunch = repository.getExisted(id);
        Lunch updated = LunchUtil.updateFromTo(lunch, lunchTo);
        updated.setRestaurant(restaurantRepository.findById(restaurantId).get());
        repository.save(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
