package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Lunch;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface LunchRepository extends BaseRepository<Lunch> {
    @Query("SELECT l FROM Lunch l WHERE l.restaurant.id = :restaurantId AND l.date = :date ORDER BY l.name")
    List<Lunch> getMenu(int restaurantId, LocalDate date);
}