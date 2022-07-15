CREATE TABLE patients
(
    id         serial primary key,
    name       text,
    city       text,
    date_birth date,
    number_oms text
);

INSERT INTO patients
VALUES (1, 'Siluanov Pavel', 'Moscow', '1986-11-21', '147258369078'),
       (2, 'Petrova Mariya', 'Moscow', '1995-05-24', '158258369056'),
       (3, 'Rezcova Yana', 'Moscow', '1991-01-06', '100258369041'),
       (4, 'Liksutov Pavel', 'Himki', '1966-07-14', '147258786037'),
       (5, 'Mohov Alexey', 'Moscow', '1977-03-13', '100147369033'),
       (6, 'Kotov Vladimir', 'Obninsk', '1985-02-14', '147456123072'),
       (7, 'Zotov Pavel', 'Himki', '1961-01-14', '102102369099'),
       (8, 'Shipova Elena', 'Moscow', '1986-03-26', '147785412087'),
       (9, 'Klimova Anna', 'Moscow', '1969-01-24', '100333369056'),
       (10, 'Pivina Olga', 'Moscow', '1985-02-11', '102312369024');

ALTER SEQUENCE patients_id_seq RESTART WITH 11;