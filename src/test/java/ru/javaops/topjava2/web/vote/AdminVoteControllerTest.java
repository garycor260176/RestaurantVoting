package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava2.web.vote.AdminVoteController.REST_URL;
import static ru.javaops.topjava2.web.vote.VoteTestData.*;

public class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllForUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "my-all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(adminVotes));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getForUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "my-current"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote2));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllForUsers() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(allVotes));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllCurrent() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "all-current"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(allVotesCurrent));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllOnDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "all-ondate")
                .param("date", LocalDate.now().minusDays(2).toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(allVotesOnDate));
    }
}
