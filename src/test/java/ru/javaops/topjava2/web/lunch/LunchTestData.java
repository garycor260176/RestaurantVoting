package ru.javaops.topjava2.web.lunch;

import ru.javaops.topjava2.model.Lunch;
import ru.javaops.topjava2.web.MatcherFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LunchTestData {
    public static final MatcherFactory.Matcher<Lunch> LUNCH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Lunch.class, "restaurant");

    public static final int LUNCH1_ID = 1;
    public static final int NOT_FOUND = 10000;

    public static final Lunch lunch1 = new Lunch(LUNCH1_ID, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now(), new BigDecimal("570.00"));
    public static final Lunch lunch2 = new Lunch(LUNCH1_ID+1, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now().minusDays(1), new BigDecimal("560.00"));
    public static final Lunch lunch3 = new Lunch(LUNCH1_ID+2, "Паштет-мусс из кролика с домашним хлебом, 50 г", LocalDate.now().minusDays(2), new BigDecimal("550.00"));

    public static final Lunch lunch4 = new Lunch(LUNCH1_ID+3, "Тартар из утки с яблоками, 200г", LocalDate.now(), new BigDecimal("880.00"));
    public static final Lunch lunch5 = new Lunch(LUNCH1_ID+4, "Тартар из утки с яблоками, 200г", LocalDate.now().minusDays(2), new BigDecimal("850.00"));

    public static final Lunch lunch6 = new Lunch(LUNCH1_ID+5, "Суп из утки с пастернаком, 260г", LocalDate.now(), new BigDecimal("850.00"));
    public static final Lunch lunch7 = new Lunch(LUNCH1_ID+6, "Суп из утки с пастернаком, 260г", LocalDate.now().minusDays(1), new BigDecimal("830.00"));

    public static final Lunch   lunch8 = new Lunch(LUNCH1_ID+7, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now(), new BigDecimal("1380.00"));
    public static final Lunch lunch9 = new Lunch(LUNCH1_ID+8, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now().minusDays(1), new BigDecimal("1350.00"));
    public static final Lunch lunch10 = new Lunch(LUNCH1_ID+9, "Крем-суп из мускусной тыквы с мясом краба, 280г", LocalDate.now().minusDays(2), new BigDecimal("1340.00"));

    public static final Lunch lunch11 = new Lunch(LUNCH1_ID+10, "Корейка молодого ягненка", LocalDate.now(), new BigDecimal("3100.00"));
    public static final Lunch lunch12 = new Lunch(LUNCH1_ID+11, "Корейка молодого ягненка", LocalDate.now().minusDays(1), new BigDecimal("3100.00"));

    public static final Lunch lunch13 = new Lunch(LUNCH1_ID+12, "Говядина по-строгановски", LocalDate.now(), new BigDecimal("2200.00"));

    public static final Lunch lunch14 = new Lunch(LUNCH1_ID+13, "Борщ с говяжьей грудинкой", LocalDate.now(), new BigDecimal("1200.00"));
    public static final Lunch lunch15 = new Lunch(LUNCH1_ID+14, "Борщ с говяжьей грудинкой", LocalDate.now().minusDays(2), new BigDecimal("1200.00"));

    public static final Lunch lunch16 = new Lunch(LUNCH1_ID+15, "Уха рыбацкая", LocalDate.now(), new BigDecimal("1400.00"));

    public static final Lunch lunch17 = new Lunch(LUNCH1_ID+16, "Баклажан запеченный с сыром", LocalDate.now(), new BigDecimal("1720.00"));
    public static final Lunch lunch18 = new Lunch(LUNCH1_ID+17, "Баклажан запеченный с сыром", LocalDate.now().minusDays(1), new BigDecimal("1720.00"));

    public static final Lunch lunch19 = new Lunch(LUNCH1_ID+18, "Суп Аргентинский фасолевый", LocalDate.now(), new BigDecimal("500.00"));
    public static final Lunch lunch20 = new Lunch(LUNCH1_ID+19, "Суп Аргентинский фасолевый", LocalDate.now().minusDays(2), new BigDecimal("500.00"));

    public static final Lunch lunch21 = new Lunch(LUNCH1_ID+20, "Гаспачо с креветками", LocalDate.now(), new BigDecimal("1250.00"));

    public static final Lunch lunch22 = new Lunch(LUNCH1_ID+21, "Жюльен с грибами", LocalDate.now(), new BigDecimal("750.00"));
    public static final Lunch lunch23 = new Lunch(LUNCH1_ID+22, "Костромские щи", LocalDate.now(), new BigDecimal("890.00"));

    public static final Lunch lunch24 = new Lunch(LUNCH1_ID+23, "Жюльен с грибами", LocalDate.of(2024,10,10), new BigDecimal("650.00"));
    public static final Lunch lunch25 = new Lunch(LUNCH1_ID+24, "Костромские щи", LocalDate.of(2024,10,10), new BigDecimal("790.00"));

    public static final List<Lunch> LUNCHES = Arrays.asList(lunch1, lunch2, lunch3, lunch4, lunch5, lunch6, lunch7, lunch8, lunch9, lunch10, lunch11,
            lunch12, lunch13, lunch14, lunch15, lunch16, lunch17, lunch18, lunch19, lunch20, lunch21, lunch22, lunch23, lunch24, lunch25);
}
