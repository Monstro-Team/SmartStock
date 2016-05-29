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

CREATE TABLE Keyy(
	product_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_name VARCHAR(50) NOT NULL,
	product_description VARCHAR(200),
	product_location VARCHAR(50),
	product_quantity INTEGER,
	product_quantity_min INTEGER
);

CREATE TABLE Part(
	product_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	product_name VARCHAR(50) NOT NULL,
	product_description VARCHAR(200),
	product_location VARCHAR(50),
	product_quantity INTEGER,
	product_quantity_min INTEGER,
	product_brand VARCHAR(50),
	product_car_type VARCHAR(50)
);

CREATE TABLE Provider(
	provider_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	provider_company VARCHAR(50) NOT NULL,
	provider_salesman VARCHAR(50),
	provider_salesmanPhone VARCHAR(20)
);

INSERT INTO User(
	username,
	password
) VALUES ("admin", "12345");

INSERT INTO Key(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES ("Chave Tetra", "Casa", "1", 10, 5);

INSERT INTO Key(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min
) VALUES("Chave Canivete", "Carro", "2", 5, 10);

INSERT INTO Part(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min,
	product_brand,
	product_car_type
) VALUES("Miolo Ignição", "Carro", "2", 5, 10, "VW", "Polo Classic");

INSERT INTO Part(
	product_name,
	product_description,
	product_location,
	product_quantity,
	product_quantity_min,
	product_brand,
	product_car_type
) VALUES("Controle Alarme", "Carro", "2", 5, 10, "VW", "Polo Classic");

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 1);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 2);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 3);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 4);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 5);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 6);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("A", 7);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 1);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 2);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 3);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 4);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 5);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 6);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("B", 7);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 1);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 2);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 3);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 4);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 5);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 6);

INSERT INTO Cabinet(
	cabinet_name,
	cabinet_drawer
) VALUES ("C", 7);
