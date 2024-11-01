package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "vote_unique_user_id_date_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity {

    @NotNull
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;

    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurantId;

    @Column(name = "date" , nullable = false, updatable = false)
    private LocalDate date;

    public Vote(Integer userId, Integer restaurant_id, LocalDate date) {
        this.userId = userId;
        this.restaurantId = restaurant_id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                '}';
    }
}