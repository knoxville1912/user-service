CREATE TABLE schedule
(
    id        serial primary key,
    doctor_id bigint REFERENCES doctors (id),
    duty_date date
);

INSERT INTO schedule
VALUES (1, 1, '2022-05-23'),
       (2, 5, '2022-05-24'),
       (3, 3, '2022-05-25'),
       (4, 4, '2022-05-26'),
       (5, 5, '2022-05-27'),
       (6, 1, '2022-05-28'),
       (7, 3, '2022-05-29');

ALTER SEQUENCE schedule_id_seq RESTART WITH 8;