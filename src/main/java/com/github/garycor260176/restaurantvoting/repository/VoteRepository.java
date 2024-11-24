package com.github.garycor260176.restaurantvoting.repository;

import com.github.garycor260176.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("select v from Vote v where v.user.id = ?1 and v.date = ?2")
    Optional<Vote> getByUserAndDate(int userId, LocalDate vote_date);

    @Query("select v from Vote v where v.user.id = ?1 and v.date>=?2 and v.date<=?3 order by v.date DESC")
    List<Vote> getByUserBetween(int userId, LocalDate fromDate, LocalDate toDate);

}
