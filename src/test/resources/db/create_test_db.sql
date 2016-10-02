DROP TABLE IF EXISTS users_info;

CREATE TABLE users_info (
  ID SERIAL UNIQUE NOT NULL,
  LOGIN VARCHAR(50) UNIQUE NOT NULL,
  NAME VARCHAR(255) NOT NULL
);

INSERT INTO users_info VALUES(0, 'andrew', 'Андрей');
INSERT INTO users_info VALUES(1, 'maks', 'Максим');
INSERT INTO users_info VALUES(2, 'john', 'Джон');
INSERT INTO users_info VALUES(3, 'vasya', 'Василий');
INSERT INTO users_info VALUES(4, 'testuser1', 'Тестовый пользователь №1');