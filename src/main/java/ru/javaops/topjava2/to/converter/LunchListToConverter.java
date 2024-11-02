package ru.javaops.topjava2.to.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.to.LunchTo;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LunchListToConverter implements Converter<List<Lunch>, List<LunchTo>> {

    private final Converter<Lunch, LunchTo> lunchToConverter;

    @Override
    public List<LunchTo> convert(List<Lunch> lunchList) {
        return lunchList.stream()
                .map(lunchToConverter::convert)
                .toList();
    }
}
