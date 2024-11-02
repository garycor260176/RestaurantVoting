package ru.javaops.topjava2.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "votes")
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/admin/votes";

    @Override
    @GetMapping("/my-all")
    public List<Vote> getAllForAuthUser() {
        return super.getAllForAuthUser();
    }

    @Override
    @GetMapping("/my-current")
    public ResponseEntity<Vote> getForAuthUser() {
        return super.getForAuthUser();
    }

    @Override
    @PostMapping()
    public ResponseEntity<Vote> toVote(int restaurantId) {
        return super.toVote(restaurantId);
    }

    @GetMapping("/all")
    public List<Vote> getAllForUsers() {
        log.info("getAllForUsers");
        return service.getAllForUsers();
    }

    @GetMapping("/all-current")
    public List<Vote> getAllCurrent() {
        log.info("getAllCurrent");
        return service.getAllForUsersOnDate(LocalDate.now());
    }

    @GetMapping("/all-ondate")
    public List<Vote> getAllForUsersOnDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getAllForUsersOnDate for date{}", date);
        return service.getAllForUsersOnDate(date);
    }
}