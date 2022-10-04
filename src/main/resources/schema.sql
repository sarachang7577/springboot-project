DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS nurse;
DROP TABLE IF EXISTS station__nurse;
CREATE TABLE station (
    station_id int NOT NULL AUTO_INCREMENT primary key,
    station_name varchar(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL
);
CREATE TABLE nurse (
    nurse_id int NOT NULL AUTO_INCREMENT primary key,
    employee_id varchar(255) NOT NULL,
    nurse_name varchar(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL
);
CREATE TABLE station__nurse (
    id int NOT NULL AUTO_INCREMENT primary key,
    station_id int NOT NULL,
    nurse_id int NOT NULL,
    created_date TIMESTAMP NOT NULL
);