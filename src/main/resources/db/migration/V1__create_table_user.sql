CREATE TABLE tb_user (
	id SERIAL PRIMARY KEY,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NOT NULL,
	username varchar(255) NOT NULL
);