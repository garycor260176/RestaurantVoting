package com.github.garycor260176.restaurantvoting.util.validation;

import com.github.garycor260176.restaurantvoting.HasId;
import com.github.garycor260176.restaurantvoting.error.IllegalRequestDataException;
import com.github.garycor260176.restaurantvoting.util.CurrentDateTime;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class ValidationUtil {
    public static final LocalTime cutoffTime = LocalTime.of(11, 0);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkNotNull(Object e) {
        if (e == null) {
            throw new IllegalRequestDataException("Entity not found!");
        }
    }

    public static void checkTime() {
        if (cutoffTime.isBefore(CurrentDateTime.getCurrentTime())) {
            throw new IllegalRequestDataException("You can't change your vote after " + cutoffTime.format(TIME_FORMATTER) + "!");
        }
    }
}