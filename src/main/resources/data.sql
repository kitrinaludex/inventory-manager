INSERT INTO users(user_name,password) VALUES('john','12345');

INSERT INTO inventories(user_id,name) VALUES (1,'testInventory');
INSERT INTO inventories(user_id,name) VALUES (1,'anothertestInventory');

INSERT INTO items(inventory_id,name,quantity) VALUES(1,'testItem',5);
INSERT INTO items(inventory_id,name,quantity) VALUES(1,'anothertestItem',15);