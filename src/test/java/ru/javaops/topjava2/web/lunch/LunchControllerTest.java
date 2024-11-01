package ru.javaops.topjava2.web.lunch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.repository.LunchRepository;
import ru.javaops.topjava2.to.LunchTo;
import ru.javaops.topjava2.util.JsonUtil;
import ru.javaops.topjava2.util.LunchUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.lunch.LunchController.REST_URL;
import static ru.javaops.topjava2.web.lunch.LunchTestData.*;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;

class LunchControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + '/';
    @Autowired
    private LunchRepository lunchRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + LUNCH1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MATCHER.contentJson(lunch1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + LUNCH1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MATCHER.contentJson(LUNCHES));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        LunchTo dishTo = new LunchTo(null, "Водка", LocalDate.now(), new BigDecimal("1000"));
        Lunch newLunch = LunchUtil.createNewFromTo(dishTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newLunch)))
                .andExpect(status().isCreated());

        Lunch created = LUNCH_MATCHER.readFromJson(action);
        int newId = created.id();
        newLunch.setId(newId);
        LUNCH_MATCHER.assertMatch(created, newLunch);
        LUNCH_MATCHER.assertMatch(lunchRepository.getExisted(newId), newLunch);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        LunchTo updatedTo = new LunchTo(null, lunch1.getName(), lunch1.getDate(), new BigDecimal("10000.00"));
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + lunch1.getId())
                .param("restaurantId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(lunchRepository.getExisted(lunch1.getId()), LunchUtil.updateFromTo(new Lunch(lunch1), updatedTo));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        LunchTo invalid = new LunchTo(null, "", lunch1.getDate(), lunch1.getPrice());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + LUNCH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + LUNCH1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(lunchRepository.findById(LUNCH1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        LunchTo invalid = new LunchTo(null, "", LocalDate.now(), new BigDecimal("7777777.00"));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}