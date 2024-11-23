package com.github.garycor260176.restaurantvoting.web.vote;

import com.github.garycor260176.restaurantvoting.error.NotFoundException;
import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.model.Vote;
import com.github.garycor260176.restaurantvoting.repository.RestaurantRepository;
import com.github.garycor260176.restaurantvoting.repository.UserRepository;
import com.github.garycor260176.restaurantvoting.repository.VoteRepository;
import com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil;
import com.github.garycor260176.restaurantvoting.web.SecurityUtil;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.garycor260176.restaurantvoting.util.DateUtil.atDayOrMax;
import static com.github.garycor260176.restaurantvoting.util.DateUtil.atDayOrMin;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Vote> getAll() {
        int userId = SecurityUtil.authId();
        log.info("get votes for user {}", userId);
        return voteRepository.getByUser(userId);
    }

    @GetMapping("/current")
    public ResponseEntity<Vote> get() {
        int userId = SecurityUtil.authId();
        log.info("get current vote for user {}", userId);
        return ResponseEntity.of(voteRepository.getByUserAndDate(userId, LocalDate.now()));
    }

    @GetMapping("/between")
    public List<Vote> getBetween(@Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                 @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        int userId = SecurityUtil.authId();
        log.info("get votes in period {} - {}", fromDate, toDate);
        return voteRepository.getByUserBetween(userId, atDayOrMin(fromDate), atDayOrMax(toDate));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<Vote> create(Integer restaurantId) {
        int userId = SecurityUtil.authId();
        log.info("create vote for user {}, restaurant = {}", userId, restaurantId);
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
        if (Objects.isNull(vote)) {
            throw new NotFoundException("Not found current vote");
        }
        log.info("update vote = {}, set restaurant = {}", vote.getId(), restaurantId);
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        ValidationUtil.checkNotNull(vote);
        ValidationUtil.checkTime();
        ValidationUtil.assureIdConsistent(restaurant, restaurantId);
        vote.setRestaurant(restaurant);
        voteRepository.save(vote);
    }
}