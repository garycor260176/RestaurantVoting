package com.github.garycor260176.restaurantvoting.web.user;

import com.github.garycor260176.restaurantvoting.model.User;
import com.github.garycor260176.restaurantvoting.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import static org.slf4j.LoggerFactory.getLogger;

@CacheConfig(cacheNames = "users")
public abstract class AbstractUserController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected UserRepository repository;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public User get(int id) {
        log.info("get {}", id);
        return repository.getExisted(id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}