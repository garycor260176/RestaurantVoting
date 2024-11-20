package com.github.garycor260176.restaurantvoting.repository;

import com.github.garycor260176.restaurantvoting.model.Dish;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
}
