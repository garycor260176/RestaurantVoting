package com.github.garycor260176.restaurantvoting.web.restaurant;

import com.github.garycor260176.restaurantvoting.model.Restaurant;
import com.github.garycor260176.restaurantvoting.to.RestaurantTo;
import com.github.garycor260176.restaurantvoting.web.MatcherFactory;

import java.util.List;

import static com.github.garycor260176.restaurantvoting.web.dish.DishTestData.*;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = 1;
    public static final int NOT_FOUND_ID = 100;

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes.restaurant");

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Ресторан Сабор де ла Вида", "Москва, ул. 1905 года, 10 стр.1");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Ресторан Зал Метрополь", "Театральный Проезд, 2. Москва");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Эль Гаучо", "Москва, Зацепский Вал, 6/13");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "Ресторан Матрёшка", "Москва,Кутузовский проспект, 2/1с6");
    public static final Restaurant restaurant4_on_20241010 = new Restaurant(RESTAURANT1_ID + 3, "Ресторан Матрёшка", "Москва,Кутузовский проспект, 2/1с6");

    public static final Restaurant restaurant1_all = new Restaurant(restaurant1);

    public static final Restaurant restaurant1_period = new Restaurant(restaurant1);

    public static final List<Restaurant> restaurants = List.of(restaurant2, restaurant4, restaurant1, restaurant3);
    public static final List<Restaurant> foundedRestaurantsByAddress = List.of(restaurant4);
    public static final List<Restaurant> foundedRestaurantsByName = List.of(restaurant2, restaurant4, restaurant1);

    public static final RestaurantTo newRestaurantTo = new RestaurantTo(null, "Обломов", "Москва, 1-ый Монетчиковский пер., д. 5");
    public static final RestaurantTo newRestaurantToDuplicate = new RestaurantTo(null, restaurant3.getName(), restaurant3.getAddress());
    public static final RestaurantTo newRestaurantToInvalid = new RestaurantTo(null, "Турандот", "мало");
    public static final RestaurantTo updateRestaurantTo = new RestaurantTo(null, "La Bottega Siciliana", "Москва, Охотный ряд, 2(ТГ Модный сезон)");
    public static final RestaurantTo updateRestaurantToInvalid = new RestaurantTo(null, null, null);
    public static final RestaurantTo updateRestaurantToDuplicate = new RestaurantTo(null, restaurant3.getName(), restaurant3.getAddress());

    static {
        restaurant1_all.setDishes(List.of(
                dish8, dish9, dish10, dish1, dish2, dish3, dish6, dish7, dish4, dish5
        ));
        restaurant1_period.setDishes(List.of(
                dish9, dish10, dish2, dish3, dish7, dish5
        ));

        restaurant1.setDishes(List.of(dish8, dish1, dish6, dish4));
        restaurant2.setDishes(List.of(dish14, dish13, dish11, dish16));
        restaurant3.setDishes(List.of(dish17, dish21, dish19));
        restaurant4.setDishes(List.of(dish22, dish23));
        restaurant4_on_20241010.setDishes(List.of(dish24, dish25));
    }
}
