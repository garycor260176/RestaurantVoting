package com.github.garycor260176.restaurantvoting.web.dish;

import com.github.garycor260176.restaurantvoting.model.Dish;
import com.github.garycor260176.restaurantvoting.to.DishTo;
import com.github.garycor260176.restaurantvoting.web.MatcherFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final int DISH1_ID = 1;
    public static final int NOT_FOUND = 10000;

    public static final Dish dish1 = new Dish(DISH1_ID, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now(), new BigDecimal("570.00"));
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now().minusDays(1), new BigDecimal("560.00"));
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now().minusDays(2), new BigDecimal("550.00"));

    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Тартар из утки с яблоками, 200г", LocalDate.now(), new BigDecimal("880.00"));
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Тартар из утки с яблоками, 200г", LocalDate.now().minusDays(2), new BigDecimal("850.00"));

    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Суп из утки с пастернаком, 260г", LocalDate.now(), new BigDecimal("850.00"));
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Суп из утки с пастернаком, 260г", LocalDate.now().minusDays(1), new BigDecimal("830.00"));

    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now(), new BigDecimal("1380.00"));
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now().minusDays(1), new BigDecimal("1350.00"));
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now().minusDays(2), new BigDecimal("1340.00"));

    public static final Dish dish11 = new Dish(DISH1_ID + 10, "Корейка молодого ягненка", LocalDate.now(), new BigDecimal("3100.00"));

    public static final Dish dish13 = new Dish(DISH1_ID + 12, "Говядина по-строгановски", LocalDate.now(), new BigDecimal("2200.00"));

    public static final Dish dish14 = new Dish(DISH1_ID + 13, "Борщ с говяжьей грудинкой", LocalDate.now(), new BigDecimal("1200.00"));

    public static final Dish dish16 = new Dish(DISH1_ID + 15, "Уха рыбацкая", LocalDate.now(), new BigDecimal("1400.00"));

    public static final Dish dish17 = new Dish(DISH1_ID + 16, "Баклажан запеченный с сыром", LocalDate.now(), new BigDecimal("1720.00"));

    public static final Dish dish19 = new Dish(DISH1_ID + 18, "Суп Аргентинский фасолевый", LocalDate.now(), new BigDecimal("500.00"));

    public static final Dish dish21 = new Dish(DISH1_ID + 20, "Гаспачо с креветками", LocalDate.now(), new BigDecimal("1250.00"));

    public static final Dish dish22 = new Dish(DISH1_ID + 21, "Жюльен с грибами", LocalDate.now(), new BigDecimal("750.00"));
    public static final Dish dish23 = new Dish(DISH1_ID + 22, "Костромские щи", LocalDate.now(), new BigDecimal("890.00"));

    public static final Dish dish24 = new Dish(DISH1_ID + 23, "Жюльен с грибами", LocalDate.of(2024, 10, 10), new BigDecimal("650.00"));
    public static final Dish dish25 = new Dish(DISH1_ID + 24, "Костромские щи", LocalDate.of(2024, 10, 10), new BigDecimal("790.00"));

    public static final DishTo newDishTo = new DishTo(null, "Новое блюдо для ресторана 2", LocalDate.now(), new BigDecimal("1000.00"));
    public static final DishTo newDishToInvalid = new DishTo(null, null, null, null);
    public static final DishTo newDishToDuplicate = new DishTo(null, dish1.getName(), dish1.getDate(), dish1.getPrice());
    public static final DishTo updateDishTo = new DishTo(null, dish1.getName(), dish1.getDate(), new BigDecimal("1000000.00"));
    public static final DishTo updateDishToInvalid = new DishTo(null, "", LocalDate.now(), new BigDecimal("100.00"));
    public static final DishTo updateDishToDuplicate = new DishTo(null, dish4.getName(), dish4.getDate(), new BigDecimal("100.00"));

}
