package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.to.LunchTo;

@UtilityClass
public class LunchUtil {
    public Lunch createNewFromTo(LunchTo lunchTo) {
        return new Lunch(null, lunchTo.getName(), lunchTo.getDate(), lunchTo.getPrice());
    }

    public Lunch updateFromTo(Lunch lunch, LunchTo lunchTo) {
        lunch.setName(lunchTo.getName());
        lunch.setDate(lunchTo.getDate());
        lunch.setPrice(lunchTo.getPrice());
        return lunch;
    }
}