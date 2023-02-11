CREATE TABLE tb_action (
	id SERIAL PRIMARY KEY,
	action_date timestamp NULL,
	description varchar(255) NULL,
	solution bool NULL,
	incident_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT fkqx6kfya4yrc4wubynwp6aurv FOREIGN KEY (incident_id) REFERENCES public.tb_incident(id),
	CONSTRAINT fksx82w1xyrh77suwh34xc5y101 FOREIGN KEY (user_id) REFERENCES public.tb_user(id)
);