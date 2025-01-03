INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES ('Ресторан Сабор де ла Вида', 'Москва, ул. 1905 года, 10 стр.1'),
       ('Ресторан Зал Метрополь', 'Театральный Проезд, 2. Москва'),
       ('Эль Гаучо', 'Москва, Зацепский Вал, 6/13'),
       ('Ресторан Матрёшка', 'Москва,Кутузовский проспект, 2/1с6');

INSERT INTO DISH (name, restaurant_id, dish_date, price)
VALUES ('Паштет-мусс из кролика с домашним хлебом, 50 г', 1, now(), 570.00),
       ('Паштет-мусс из кролика с домашним хлебом, 50 г', 1, now() - 1, 560.00),
       ('Паштет-мусс из кролика с домашним хлебом, 50 г', 1, now() - 2, 550.00),
       ('Тартар из утки с яблоками, 200г', 1, now(), 880.00),
       ('Тартар из утки с яблоками, 200г', 1, now() - 2, 850.00),
       ('Суп из утки с пастернаком, 260г', 1, now(), 850.00),
       ('Суп из утки с пастернаком, 260г', 1, now() - 1, 830.00),
       ('Крем-суп из мускусной тыквы с мясом краба, 280г', 1, now(), 1380.00),
       ('Крем-суп из мускусной тыквы с мясом краба, 280г', 1, now() - 1, 1350.00),
       ('Крем-суп из мускусной тыквы с мясом краба, 280г', 1, now() - 2, 1340.00),

       ('Корейка молодого ягненка', 2, now(), 3100.00),
       ('Корейка молодого ягненка', 2, now() - 1, 3100.00),
       ('Говядина по-строгановски', 2, now(), 2200.00),
       ('Борщ с говяжьей грудинкой', 2, now(), 1200.00),
       ('Борщ с говяжьей грудинкой', 2, now() - 2, 1200.00),
       ('Уха рыбацкая', 2, now(), 1400),

       ('Баклажан запеченный с сыром', 3, now(), 1720.00),
       ('Баклажан запеченный с сыром', 3, now() - 1, 1720.00),
       ('Суп Аргентинский фасолевый', 3, now(), 500.00),
       ('Суп Аргентинский фасолевый', 3, now() - 2, 500.00),
       ('Гаспачо с креветками', 3, now(), 1250.00),

       ('Жюльен с грибами', 4, now(), 750.00),
       ('Костромские щи', 4, now(), 890.00),

       ('Жюльен с грибами', 4, '2024-10-10', 650.00),
       ('Костромские щи', 4, '2024-10-10', 790.00);

INSERT INTO VOTE (vote_date, restaurant_id, user_id)
VALUES (now(), 1, 1),
       (now(), 2, 2),

       (now() - 1, 3, 1),
       (now() - 1, 4, 2),
       (now() - 1, 4, 3),

       (now() - 2, 1, 1),
       (now() - 2, 2, 2),
       (now() - 2, 2, 3);