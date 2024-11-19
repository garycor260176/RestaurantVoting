package com.github.garycor260176.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "uk_user_vote_date"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Vote extends BaseEntity {
    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnoreProperties(value = {"address", "dishes"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote(Integer id, LocalDate date, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(LocalDate date, Restaurant restaurant, User user) {
        this(null, date, restaurant, user);
    }
}
