INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name, address)
VALUES
    ('Ресторан Сабор де ла Вида', 'Москва, ул. 1905 года, 10 стр.1'),
    ('Ресторан Зал Метрополь', 'Театральный Проезд, 2. Москва'),
    ('Эль Гаучо', 'Москва, Зацепский Вал, 6/13'),
    ('Ресторан Матрёшка', 'Москва,Кутузовский проспект, 2/1с6');

INSERT INTO LUNCH (name, date, price, restaurant_id)
VALUES
    ('Паштет-мусс из кролика с домашним хлебом, 50 г', now(), 570.00, 1),
    ('Паштет-мусс из кролика с домашним хлебом, 50 г', now()-1, 560.00, 1),
    ('Паштет-мусс из кролика с домашним хлебом, 50 г', now()-2, 550.00, 1),
    ('Тартар из утки с яблоками, 200г', now(), 880.00, 1),
    ('Тартар из утки с яблоками, 200г', now()-2, 850.00, 1),
    ('Суп из утки с пастернаком, 260г', now(), 850.00, 1),
    ('Суп из утки с пастернаком, 260г', now()-1, 830.00, 1),
    ('Крем-суп из мускусной тыквы с мясом краба, 280г', now(), 1380.00, 1),
    ('Крем-суп из мускусной тыквы с мясом краба, 280г', now()-1, 1350.00, 1),
    ('Крем-суп из мускусной тыквы с мясом краба, 280г', now()-2, 1340.00, 1),

    ('Корейка молодого ягненка', now(), 3100.00, 2),
    ('Корейка молодого ягненка', now()-1, 3100.00, 2),
    ('Говядина по-строгановски', now(), 2200.00, 2),
    ('Борщ с говяжьей грудинкой', now(), 1200.00, 2),
    ('Борщ с говяжьей грудинкой', now()-2, 1200.00, 2),
    ('Уха рыбацкая', now(), 1400, 2),

    ('Баклажан запеченный с сыром', now(), 1720.00, 3),
    ('Баклажан запеченный с сыром', now()-1, 1720.00, 3),
    ('Суп Аргентинский фасолевый', now(), 500.00, 3),
    ('Суп Аргентинский фасолевый', now()-2, 500.00, 3),
    ('Гаспачо с креветками', now(), 1250.00, 3),

    ('Жюльен с грибами', now(), 750.00, 4),
    ('Костромские щи', now(), 890.00, 4),

    ('Жюльен с грибами', '2024-10-10', 650.00, 4),
    ('Костромские щи', '2024-10-10', 790.00, 4);

INSERT INTO VOTE (date, restaurant_id, user_id)
VALUES (now(), 1, 1),
       (now(), 2, 2),

       (now()-1, 3, 1),
       (now()-1, 4, 2),
       (now()-1, 4, 3),

       (now()-2, 1, 1),
       (now()-2, 2, 2),
       (now()-2, 2, 3);