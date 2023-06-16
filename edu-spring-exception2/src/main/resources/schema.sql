/* Drop Tables */
DROP TABLE IF EXISTS employee;

/* Create Tables */
CREATE TABLE employee
(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10),
    joined_date DATE,
    department_name VARCHAR(20),
    email VARCHAR(256),
    birth_day DATE
);