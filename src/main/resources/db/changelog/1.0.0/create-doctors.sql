CREATE TABLE doctors
(
    id         serial primary key,
    name       text,
    date_birth date,
    speciality text,
    mail       text,
    phone      text,
    start_date date,
    end_date   date
);

INSERT INTO doctors
VALUES (1, 'Simonov Ivan', '1986-10-24', 'Therapist', 'dachok1912@mail.ru', '89661230001', '2014-04-05', null),
       (2, 'Ivanov Petr', '1974-01-21', 'Oncologist', 'ivanov_petr@clinic.com', '89661230002', '2014-03-03',
        '2019-02-24'),
       (3, 'Penikina Svetlana', '1976-05-14', 'Therapist', 'budvapragueriga2016@mail.ru', '89661230003', '2015-06-08',
        null),
       (4, 'Voynova Ekaterina', '1979-07-27', 'Therapist', 'javatest2022@mail.ru', '89661230004', '2016-03-09',
        null),
       (5, 'Kuznetsov Evgeniy', '1990-03-05', 'Oncologist', 'javatest2022@mail.ru', '89661230005', '2019-07-01',
        null),
       (6, 'Lomov Mihail', '1987-12-12', 'Therapist', 'javatest2022@mail.ru', '89661230006', '2022-06-02',
        null);

ALTER SEQUENCE doctors_id_seq RESTART WITH 7;