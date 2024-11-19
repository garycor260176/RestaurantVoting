package com.github.garycor260176.restaurantvoting.util;

import java.time.LocalDate;

public class DateUtil {
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(9999, 12, 31);

    public static LocalDate atDayOrMin(LocalDate localDate) {
        return localDate != null ? localDate : MIN_DATE;
    }

    public static LocalDate atDayOrMax(LocalDate localDate) {
        return localDate != null ? localDate : MAX_DATE;
    }
}
