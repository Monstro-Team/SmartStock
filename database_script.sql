CREATE DATABASE smart_stock;

CREATE TABLE user(
	user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(25) NOT NULL
);

CREATE TABLE cabinet(
	cabinet_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	cabinet_name VARCHAR(50) NOT NULL,
	cabinet_drawer INTEGER
);

CREATE TABLE stock(
	stock_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_id INTEGER NOT NULL,
	stock_price INTEGER,
	stock_supplier VARCHAR(50),
	stock_quantity INTEGER
);

CREATE TABLE product(
	product_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_name VARCHAR(50) NOT NULL,
	product_description VARCHAR(200),
	product_location VARCHAR(50),
	product_quantity INTEGER,
	product_quantity_min INTEGER
);

INSERT INTO user(
	username,
	password
) VALUES ("admin", "12345");

INSERT INTO product(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES ("Chave Tetra", "Casa", "A1", 10, 5);

INSERT INTO product(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES("Chave Canivete", "Carro", "A2", 5, 10);