package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.util.validation.ValidationUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;
import ru.javaops.topjava2.web.restaurant.RestaurantTestData;
import ru.javaops.topjava2.web.user.UserTestData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.user.UserTestData.GUEST_MAIL;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava2.web.vote.UserVoteController.REST_URL;
import static ru.javaops.topjava2.web.vote.VoteTestData.*;

public class UserVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getUserVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(userVotes));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getUserVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "current"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1));
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void getGuestVoteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "current"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void revotingUserBeforeTime() throws Exception {
        ValidationUtil.setCUT_OFF_TIME(LocalTime.now().plusMinutes(10));

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Vote created = VOTE_MATCHER.readFromJson(action);
        Vote newVote = new Vote(LocalDate.now(), RestaurantTestData.restaurant2, UserTestData.user);
        newVote.setId(created.getId());
        VOTE_MATCHER.assertMatch(created, newVote);
        assertEquals(repository.getExisted(newVote.getId()), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void revotingUserAfterTime() throws Exception {
        LocalTime time = LocalTime.now().minusMinutes(10);
        if(!time.isAfter(LocalTime.now())) {
            ValidationUtil.setCUT_OFF_TIME(LocalTime.now().minusMinutes(10));
        }

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void createGuestVote() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        Vote created = VOTE_MATCHER.readFromJson(action);
        Vote newVote = new Vote(LocalDate.now(), RestaurantTestData.restaurant2, UserTestData.guest);
        newVote.setId(created.getId());
        VOTE_MATCHER.assertMatch(created, newVote);
        assertEquals(repository.getExisted(newVote.getId()), newVote);
    }
}
