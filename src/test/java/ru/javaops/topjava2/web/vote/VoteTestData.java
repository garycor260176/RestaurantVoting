package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.web.MatcherFactory;
import ru.javaops.topjava2.web.restaurant.RestaurantTestData;
import ru.javaops.topjava2.web.user.UserTestData;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class,
            "restaurant.address", "restaurant.lunches", "restaurant.name",
            "user");

    public static final int VOTE1_ID = 1;

    public static Vote vote1 = new Vote(VOTE1_ID, LocalDate.now(), RestaurantTestData.restaurant1, UserTestData.user);
    public static Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.now(), RestaurantTestData.restaurant2, UserTestData.admin);

    public static Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now().minusDays(1), RestaurantTestData.restaurant3, UserTestData.user);
    public static Vote vote4 = new Vote(VOTE1_ID + 3, LocalDate.now().minusDays(1), RestaurantTestData.restaurant4, UserTestData.admin);
    public static Vote vote5 = new Vote(VOTE1_ID + 4, LocalDate.now().minusDays(1), RestaurantTestData.restaurant4, UserTestData.guest);

    public static Vote vote6 = new Vote(VOTE1_ID + 5, LocalDate.now().minusDays(2), RestaurantTestData.restaurant1, UserTestData.user);
    public static Vote vote7 = new Vote(VOTE1_ID + 6, LocalDate.now().minusDays(2), RestaurantTestData.restaurant2, UserTestData.admin);
    public static Vote vote8 = new Vote(VOTE1_ID + 7, LocalDate.now().minusDays(2), RestaurantTestData.restaurant2, UserTestData.guest);

    public static List<Vote> userVotes = List.of(vote1, vote3, vote6);
    public static List<Vote> adminVotes = List.of(vote2, vote4, vote7);
    public static List<Vote> allVotes = List.of(vote2, vote1, vote4, vote5, vote3, vote7, vote8, vote6);
    public static List<Vote> allVotesCurrent = List.of(vote2, vote1);
    public static List<Vote> allVotesOnDate = List.of(vote7, vote8, vote6);

}
