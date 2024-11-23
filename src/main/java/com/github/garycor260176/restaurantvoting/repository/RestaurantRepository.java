package com.github.garycor260176.restaurantvoting.repository;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE lower(concat('%', ?1, '%')) and LOWER(r.address) LIKE lower(concat('%', ?2, '%')) order by r.name")
    List<Restaurant> getByNameAndAddressIgnoreCase(String name, String address);

    @Query("select r from Restaurant r LEFT JOIN fetch r.dishes d where r.id=?1 and d.date>=?2 and d.date<=?3 order by d.name, d.date DESC")
    Optional<Restaurant> getWithDishesBetween(int id, LocalDate fromDate, LocalDate toDate);

    @Query("select r from Restaurant r LEFT JOIN fetch r.dishes d where d.date=?1 order by r.name, d.name")
    List<Restaurant> getAllWithDishesOnDate(LocalDate date);

}
