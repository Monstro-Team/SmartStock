CREATE DATABASE smart_stock;
USE smart_stock;
CREATE TABLE User(
	user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(25) NOT NULL
);

CREATE TABLE Cabinet(
	cabinet_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	cabinet_name VARCHAR(50) NOT NULL,
	cabinet_drawer INTEGER
);

CREATE TABLE Stock(
	stock_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_id INTEGER NOT NULL,
	stock_price INTEGER,
	stock_supplier VARCHAR(50),
	stock_quantity INTEGER
);

CREATE TABLE Product(
	product_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_name VARCHAR(50) NOT NULL,
	product_description VARCHAR(200),
	product_location VARCHAR(50),
	product_quantity INTEGER,
	product_quantity_min INTEGER
);

CREATE TABLE Provider(
	provider_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	provider_conpany VARCHAR(50) NOT NULL,
	provider_salesman VARCHAR(50),
	provider_salesmanPhone VARCHAR(20)
);

INSERT INTO User(
	username,
	password
) VALUES ("admin", "12345");

INSERT INTO Product(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES ("Chave Tetra", "Casa", "A1", 10, 5);

INSERT INTO Product(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES("Chave Canivete", "Carro", "A2", 5, 10);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 1);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 2);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 3);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 4);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 5);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 6);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("a", 7);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 1);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 2);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 3);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 4);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 5);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 6);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("b", 7);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 1);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 2);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 3);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 4);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 5);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 6);
INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("c", 7);