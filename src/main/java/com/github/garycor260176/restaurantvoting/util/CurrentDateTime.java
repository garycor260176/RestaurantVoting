package com.github.garycor260176.restaurantvoting.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class CurrentDateTime {
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }
}