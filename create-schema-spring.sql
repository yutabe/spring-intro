DROP DATABASE IF EXISTS spring;
CREATE DATABASE spring CHARACTER SET utf8mb4;
DROP USER IF EXISTS 'user'@'localhost';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'P@ssw0rd';
GRANT ALL PRIVILEGES ON spring.* to 'user'@'localhost';
USE spring;
SHOW DATABASES;
