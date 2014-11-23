SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS product_type_a;
DROP TABLE IF EXISTS product_type_b;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS rel_child_product_tag;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS product_tag;


/* Create Tables */

CREATE TABLE product_type_a
(
	product_id int NOT NULL,
	a_name varchar(32) NOT NULL,
	PRIMARY KEY (product_id)
);


CREATE TABLE product_type_b
(
	product_id int NOT NULL,
	b_count int NOT NULL,
	PRIMARY KEY (product_id)
);


CREATE TABLE history
(
	history_id int NOT NULL AUTO_INCREMENT,
	product_id int NOT NULL,
	quantity int NOT NULL,
	amount int NOT NULL,
	PRIMARY KEY (history_id)
);


CREATE TABLE product
(
	product_id int NOT NULL,
	category_id int NOT NULL,
	product_name varchar(32) NOT NULL,
	product_type int NOT NULL,
	PRIMARY KEY (product_id)
);


CREATE TABLE product_category
(
	category_id int NOT NULL,
	product_category_name varchar(32) NOT NULL,
	PRIMARY KEY (category_id)
);


CREATE TABLE rel_child_product_tag
(
	product_id int NOT NULL,
	product_tag_id int NOT NULL,
	PRIMARY KEY (product_id, product_tag_id)
);


CREATE TABLE product_tag
(
	product_tag_id int NOT NULL,
	product_tag_name varchar(32) NOT NULL,
	PRIMARY KEY (product_tag_id)
);



/* Create Foreign Keys */

ALTER TABLE product_type_a
	ADD FOREIGN KEY (product_id)
	REFERENCES product (product_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE product_type_b
	ADD FOREIGN KEY (product_id)
	REFERENCES product (product_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rel_child_product_tag
	ADD FOREIGN KEY (product_id)
	REFERENCES product (product_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE history
	ADD FOREIGN KEY (product_id)
	REFERENCES product (product_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE product
	ADD FOREIGN KEY (category_id)
	REFERENCES product_category (category_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE rel_child_product_tag
	ADD FOREIGN KEY (product_tag_id)
	REFERENCES product_tag (product_tag_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
