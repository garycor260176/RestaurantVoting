package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public List<Vote> getAllForAuthUser(int userId) {
        return voteRepository.findAllByUserId(userId);
    }

    public List<Vote> getAllForUsers() {
        return voteRepository.getAll();
    }

    public List<Vote> getAllForUsersOnDate(LocalDate date) {
        return voteRepository.findAllByDate(date);
    }

    public Optional<Vote> getForAuthUser(int userId) {
        Vote vote = voteRepository.findByUserIdAndDate(userId, LocalDate.now()).orElseThrow(() -> new NotFoundException("Not found"));
        return Optional.ofNullable(vote);
    }

    public Optional<Vote> toVote(int userId, int restaurantId) {
        LocalDate currentDate = LocalDate.now( );
        Restaurant restaurant = restaurantRepository.getExisted(restaurantId);
        Vote vote = voteRepository.findByUserIdAndDate(userId, currentDate)
                .orElse( new Vote(currentDate, restaurant, userRepository.getExisted(userId)));
        if(vote.isNew()) {
            voteRepository.save(vote);
        } else {
            if(!Objects.equals(vote.getRestaurant().getId(), restaurantId)) {
                if(ValidationUtil.CUT_OFF_TIME.isAfter(LocalTime.now())){
                    vote.setRestaurant(restaurant);
                    voteRepository.save(vote);
                } else {
                    throw new DataIntegrityViolationException("The user can change his vote only until 11:00");
                }
            }
        }
        return Optional.ofNullable(vote);
    }
}
