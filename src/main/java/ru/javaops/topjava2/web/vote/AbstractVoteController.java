package ru.javaops.topjava2.web.vote;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.service.VoteService;
import ru.javaops.topjava2.web.SecurityUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractVoteController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected VoteService service;

    public List<Vote> getAllForAuthUser() {
        int userId = SecurityUtil.authUser().getId();
        log.info("getAllForUser for user{}", userId);
        return service.getAllForAuthUser(userId);
    }

    public ResponseEntity<Vote> getForAuthUser() {
        int userId = SecurityUtil.authUser().getId();
        log.info("getCurrentForUser for user{}", userId);
        return ResponseEntity.of(service.getForAuthUser(userId));
    }

    public ResponseEntity<Vote> toVote(int restaurantId) {
        int userId = SecurityUtil.authUser().getId();
        log.info("toVote for user{}", userId);
        return ResponseEntity.of(service.toVote(userId, restaurantId));
    }
}