CREATE TABLE medical_histories
(
    id                   serial primary key,
    doctor_id            bigint REFERENCES doctors (id),
    patient_id           bigint REFERENCES patients (id),
    diagnosis            text,
    start_date           date,
    end_date             date
);

INSERT INTO medical_histories
VALUES (1, 2, 1, 'Tumor', '2015-02-28', '2015-03-05'),
       (2, 1, 2, 'Hernia', '2016-01-14', '2016-01-19'),
       (3, 4, 3, 'Sarcoidosis', '2016-05-14', '2016-06-17'),
       (4, 3, 1, 'Lymphoma', '2016-07-11', '2016-07-25'),
       (5, 2, 4, 'Infectious mononucleosis', '2018-05-17', '2018-05-26'),
       (6, 4, 5, 'Hernia', '2022-05-18', null),
       (7, 1, 6, 'Sarcoidosis', '2022-05-18', null),
       (8, 5, 4, 'Pneumonia', '2022-05-21', null),
       (9, 3, 7, 'Diabetes', '2022-05-22', null),
       (10, 4, 8, 'Arthritis', '2022-05-22', null),
       (11, 3, 9, 'Asthma', '2022-05-24', null),
       (12, 3, 10, 'Ulcer', '2022-05-24', null),
       (13, 1, 3, 'Gastritis', '2022-05-25', null),
       (14, 4, 1, 'Hernia', '2022-06-26', null);

ALTER SEQUENCE medical_histories_id_seq RESTART WITH 15;