package ru.javaops.topjava2.web.restaurant;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractRestaurantController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected RestaurantRepository repository;
    @Autowired
    private UniqueNameAddressValidator nameValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(nameValidator);
    }

    public ResponseEntity<Restaurant> getWithCurrentLunches(int id) {
        log.info("get restaurant{} with menu on date", id);
        ValidationUtil.assureIdConsistent(repository.getExisted(id), id);
        return ResponseEntity.of(repository.getWithLunchesOnDate(id, LocalDate.now()));
    }

    public List<Restaurant> getAllWithCurrentLunches() {
        log.info("getAllWithCurrentLunches");
        return repository.getAllWithLunchesOnDate(LocalDate.now());
    }
}