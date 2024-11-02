package ru.javaops.topjava2.web.vote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.Vote;

import java.util.List;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "votes")
@RequiredArgsConstructor
public class UserVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/user/votes";

    @Override
    @GetMapping("/all")
    public List<Vote> getAllForAuthUser() {
        return super.getAllForAuthUser();
    }

    @Override
    @GetMapping("/current")
    public ResponseEntity<Vote> getForAuthUser() {
        return super.getForAuthUser();
    }

    @Override
    @PostMapping()
    public ResponseEntity<Vote> toVote(int restaurantId) {
        return super.toVote(restaurantId);
    }

}