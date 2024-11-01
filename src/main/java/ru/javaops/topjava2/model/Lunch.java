package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "lunch")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lunch extends NamedEntity {
    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "price", nullable = false)
    @Range(min = 0, max = 50000)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonBackReference
    private Restaurant restaurant;

    public Lunch(Integer id, String name, LocalDate date, BigDecimal price) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public Lunch(Lunch lunch) {
        this(lunch.id, lunch.name, lunch.date, lunch.price);
    }

    public int getRestaurantId( ) {
        return Objects.isNull(restaurant) ? 0 : restaurant.getId();
    }
}
