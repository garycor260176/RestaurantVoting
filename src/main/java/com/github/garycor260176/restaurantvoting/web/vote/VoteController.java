package com.github.garycor260176.restaurantvoting.web.vote;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.model.Vote;
import com.github.garycor260176.restaurantvoting.repository.RestaurantRepository;
import com.github.garycor260176.restaurantvoting.repository.UserRepository;
import com.github.garycor260176.restaurantvoting.repository.VoteRepository;
import com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil;
import com.github.garycor260176.restaurantvoting.web.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public VoteController(VoteRepository voteRepository, RestaurantRepository restaurantRepository,
                          UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Vote> getAll() {
        int userId = SecurityUtil.authId();
        log.info("get votes for user{}", userId);
        return voteRepository.getAllById(userId);
    }

    @GetMapping("/current")
    public ResponseEntity<Vote> get() {
        int userId = SecurityUtil.authId();
        log.info("getTodayVote for user{}", userId);
        return ResponseEntity.of(voteRepository.findByIdAndDate(userId, LocalDate.now()));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<Vote> create(Integer restaurantId) {
        int userId = SecurityUtil.authId();
        Vote created = new Vote(LocalDate.now(), restaurantRepository.getExisted(restaurantId), userRepository.getExisted(userId));
        voteRepository.save(created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(Integer restaurantId) {
        Vote vote = get().getBody();
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        ValidationUtil.checkNotNull(vote);
        ValidationUtil.checkTime();
        ValidationUtil.assureIdConsistent(restaurant, restaurantId);
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
    }
}