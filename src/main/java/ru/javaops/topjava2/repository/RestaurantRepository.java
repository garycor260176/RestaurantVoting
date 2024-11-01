package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) = LOWER(:name) and LOWER(r.address) = LOWER(:address)")
    Optional<Restaurant> findByNameAndAddressIgnoreCase(String name, String address);

    @Query("select r from Restaurant r left join fetch r.lunches l where r.id=?1 and l.date=?2 order by l.name")
    Optional<Restaurant> getWithLunchesOnDate(int id, LocalDate date);

    @Query("select r from Restaurant r left join fetch r.lunches l where l.date=?1 order by r.name, l.name")
    List<Restaurant> getAllWithLunchesOnDate(@Param("date")LocalDate date);
}