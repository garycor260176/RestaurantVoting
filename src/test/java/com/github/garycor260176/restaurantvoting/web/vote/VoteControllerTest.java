package com.github.garycor260176.restaurantvoting.web.vote;

import com.github.garycor260176.restaurantvoting.model.Vote;
import com.github.garycor260176.restaurantvoting.repository.VoteRepository;
import com.github.garycor260176.restaurantvoting.util.CurrentDateTime;
import com.github.garycor260176.restaurantvoting.util.validation.ValidationUtil;
import com.github.garycor260176.restaurantvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static com.github.garycor260176.restaurantvoting.web.user.UserTestData.GUEST_MAIL;
import static com.github.garycor260176.restaurantvoting.web.user.UserTestData.USER_MAIL;
import static com.github.garycor260176.restaurantvoting.web.vote.VoteController.REST_URL;
import static com.github.garycor260176.restaurantvoting.web.vote.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getUserVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(userVotes));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getCurrentVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "current"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1));
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void getCurrentVoteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "current"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void getGuestVoteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "current"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void create() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "2"))
                .andExpect(status().isCreated())
                .andDo(print());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        Vote newVote = VoteTestData.getNewGuestVote();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        assertEquals(repository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void createWithNotExistRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "10"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createSecondCurrentVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "3"))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        if (ValidationUtil.cutoffTime.isAfter(LocalTime.now())) {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", "2"))
                    .andExpect(status().isNoContent())
                    .andDo(print());
            assertEquals(repository.getExisted(VOTE1_ID), VoteTestData.getUserUpdated());
        } else {
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", "2"))
                    .andExpect(status().isUnprocessableEntity())
                    .andDo(print());
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateBeforeCutoffTime() throws Exception {
        try (MockedStatic<CurrentDateTime> localTimeMockedStatic = Mockito.mockStatic(CurrentDateTime.class)) {
            localTimeMockedStatic.when(CurrentDateTime::getCurrentTime).thenReturn(LocalTime.of(10, 0, 0));
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", "2"))
                    .andExpect(status().isNoContent())
                    .andDo(print());
            assertEquals(repository.getExisted(VOTE1_ID), VoteTestData.getUserUpdated());
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAfterCutoffTime() throws Exception {
        try (MockedStatic<CurrentDateTime> localTimeMockedStatic = Mockito.mockStatic(CurrentDateTime.class)) {
            localTimeMockedStatic.when(CurrentDateTime::getCurrentTime).thenReturn(LocalTime.of(12, 0, 0));
            perform(MockMvcRequestBuilders.put(REST_URL)
                    .param("restaurantId", "2"))
                    .andExpect(status().isUnprocessableEntity())
                    .andDo(print());
        }
    }
}
