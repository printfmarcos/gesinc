CREATE TABLE tb_incident (
	id SERIAL PRIMARY KEY,
	closing_date timestamp NULL,
	description varchar(255) NULL,
	incident_type varchar(255) NULL,
	opening_date timestamp NULL,
	priority varchar(255) NULL,
	status varchar(255) NULL,
	updated_at timestamp NULL,
	attendant_id int8 NULL,
	requester_id int8 NULL,
	CONSTRAINT fk2vxcnb9p1qikldyaun7lwp794 FOREIGN KEY (attendant_id) REFERENCES public.tb_user(id),
	CONSTRAINT fklfarejum8ejj0ogvajghu2s6u FOREIGN KEY (requester_id) REFERENCES public.tb_user(id)
);