DROP TABLE IF EXISTS test,users,items,inventories;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    user_name TEXT,
    password TEXT);

CREATE TABLE items(
id SERIAL PRIMARY KEY,
inventory_id INT,
name TEXT,
quantity INT);

CREATE TABLE inventories(
id SERIAL PRIMARY KEY,
user_id INT,
name TEXT);