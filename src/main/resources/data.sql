INSERT INTO station(station_name, created_date, updated_date) VALUES ('station01', '2022-10-10 00:00:00', '2022-10-10 00:00:00');
INSERT INTO station(station_name, created_date, updated_date) VALUES ('station02', '2022-10-10 01:00:00', '2022-10-10 01:00:00');
INSERT INTO station(station_name, created_date, updated_date) VALUES ('station03', '2022-10-10 02:00:00', '2022-10-10 02:00:00');

INSERT INTO nurse(employee_id, nurse_name, created_date, updated_date) VALUES ('TW-01', 'nurse01', '2022-10-10 03:00:00', '2022-10-10 03:00:00');
INSERT INTO nurse(employee_id, nurse_name, created_date, updated_date) VALUES ('TW-02', 'nurse02', '2022-10-10 03:00:00', '2022-10-10 03:00:00');
INSERT INTO nurse(employee_id, nurse_name, created_date, updated_date) VALUES ('TW-03', 'nurse03', '2022-10-10 03:00:00', '2022-10-10 03:00:00');

INSERT INTO station__nurse(station_id, nurse_id, created_date) VALUES (1, 1, '2022-10-10 04:00:00');
INSERT INTO station__nurse(station_id, nurse_id, created_date) VALUES (1, 2, '2022-10-10 04:00:00');
INSERT INTO station__nurse(station_id, nurse_id, created_date) VALUES (1, 3, '2022-10-10 04:00:00');