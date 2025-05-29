DROP TABLE IF EXISTS test,users,items,inventories,inventory_authorities;

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

CREATE TABLE inventory_authorities(
user_id INT,
inventory_id INT,
authority_level INT);