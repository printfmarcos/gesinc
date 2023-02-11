CREATE TABLE tb_role (
	id SERIAL PRIMARY KEY,
	role_name varchar(255) NOT NULL,
	CONSTRAINT uk_c9lijtmr0x68iu1vxftbu2u33 UNIQUE (role_name)
);