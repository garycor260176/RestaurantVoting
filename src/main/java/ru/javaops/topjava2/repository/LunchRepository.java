package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Lunch;

@Transactional(readOnly = true)
public interface LunchRepository extends BaseRepository<Lunch> {

}