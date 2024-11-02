package ru.javaops.topjava2.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("select v from Vote v where v.user.id = ?1 order by v.date DESC")
    List<Vote> findAllByUserId(int userId);

    @Query("select v from Vote v order by v.date DESC, v.user.name")
    List<Vote>  getAll();

    @Query("select v from Vote v where v.user.id = ?1 and v.date = ?2")
    Optional<Vote> findByUserIdAndDate(int userId, @NotNull LocalDate date);

    @Query("select v from Vote v where v.date = ?1 order by v.user.name")
    List<Vote> findAllByDate(LocalDate date);
}