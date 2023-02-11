CREATE TABLE tb_users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT fk6p4o2kxbq23rthm174k19xo2h FOREIGN KEY (role_id) REFERENCES public.tb_role(id),
	CONSTRAINT fk85qorv8qojsxvl1nv56vckxmj FOREIGN KEY (user_id) REFERENCES public.tb_user(id)
);