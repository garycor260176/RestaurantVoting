package com.github.garycor260176.restaurantvoting.web.vote;

import com.github.garycor260176.restaurantvoting.model.Vote;
import com.github.garycor260176.restaurantvoting.web.MatcherFactory;
import com.github.garycor260176.restaurantvoting.web.restaurant.RestaurantTestData;
import com.github.garycor260176.restaurantvoting.web.user.UserTestData;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class,
            "restaurant.address", "restaurant.dishes", "restaurant.name",
            "user");

    public static final int VOTE1_ID = 1;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now(), RestaurantTestData.restaurant1, UserTestData.user);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now().minusDays(1), RestaurantTestData.restaurant3, UserTestData.user);
    public static final Vote vote6 = new Vote(VOTE1_ID + 5, LocalDate.now().minusDays(2), RestaurantTestData.restaurant1, UserTestData.user);
    public static final List<Vote> userVotes = List.of(vote1, vote3, vote6);
    public static final List<Vote> userVotesBetween = List.of(vote3, vote6);

    public static Vote getNewGuestVote() {
        return new Vote(null, LocalDate.now(), RestaurantTestData.restaurant2, UserTestData.guest);
    }

    public static Vote getUserUpdated() {
        return new Vote(VOTE1_ID, LocalDate.now(), RestaurantTestData.restaurant2, UserTestData.user);
    }

}
