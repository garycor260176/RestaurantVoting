package ru.javaops.topjava2.to.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.to.LunchTo;

@Component
public class LunchToConverter implements Converter<Lunch, LunchTo> {
    @Override
    public LunchTo convert(Lunch lunch) {
        return new LunchTo(lunch.getId(), lunch.getName(), lunch.getDate(), lunch.getPrice());
    }
}
