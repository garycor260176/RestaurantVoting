package ru.javaops.topjava2.web.restaurant;

import org.assertj.core.api.Assertions;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.RestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.util.List;

import static ru.javaops.topjava2.web.lunch.LunchTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "lunches");
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_LUNCH_MATCHER = MatcherFactory.usingAssertions(Restaurant.class,
            (a, e) -> Assertions.assertThat(a).usingRecursiveComparison().ignoringFields("lunches.restaurant").isEqualTo(e),
            (a, e) -> {
                throw new UnsupportedOperationException();
            });
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int NOT_FOUND_ID = 100;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Ресторан Сабор де ла Вида", "Москва, ул. 1905 года, 10 стр.1");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Ресторан Зал Метрополь", "Театральный Проезд, 2. Москва");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Эль Гаучо", "Москва, Зацепский Вал, 6/13");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "Ресторан Матрёшка", "Москва,Кутузовский проспект, 2/1с6");
    public static final Restaurant restaurant4_on_20241010 = new Restaurant(RESTAURANT1_ID + 3, "Ресторан Матрёшка", "Москва,Кутузовский проспект, 2/1с6");

    public static final List<Restaurant> restaurants = List.of(restaurant2, restaurant4, restaurant1, restaurant3);

    static {
        restaurant1.setLunches(List.of(lunch8, lunch1, lunch6, lunch4));
        restaurant2.setLunches(List.of(lunch14, lunch13, lunch11, lunch16));
        restaurant3.setLunches(List.of(lunch17, lunch21, lunch19));
        restaurant4.setLunches(List.of(lunch22, lunch23));
        restaurant4_on_20241010.setLunches(List.of(lunch24, lunch25));
    }
}
