package com.github.garycor260176.restaurantvoting.repository;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("select r from Restaurant r LEFT JOIN fetch r.dishes d where r.id=?1 and d.dish_date=?2 order by d.name")
    Optional<Restaurant> getWithDishes(int id, LocalDate date);

    @Query("select r from Restaurant r LEFT JOIN fetch r.dishes d where r.id=?1 order by d.dish_date DESC, d.name")
    Optional<Restaurant> getWithAllDishes(int id);

    @Query("select r from Restaurant r LEFT JOIN fetch r.dishes d where d.dish_date=?1 order by r.name, d.name")
    List<Restaurant> getAllWithDishes(LocalDate date);

}
