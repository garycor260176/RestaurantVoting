package com.github.garycor260176.restaurantvoting.util;

import com.github.garycor260176.restaurantvoting.model.Role;
import com.github.garycor260176.restaurantvoting.model.User;
import com.github.garycor260176.restaurantvoting.to.UserTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsersUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}