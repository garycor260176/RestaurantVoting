package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LunchTo extends NamedTo {
    @NotNull
    LocalDate date;

    @NotNull
    @Range(min = 0, max = 500000)
    BigDecimal price;

    public LunchTo(Integer id, String name, LocalDate date, BigDecimal price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }
}
