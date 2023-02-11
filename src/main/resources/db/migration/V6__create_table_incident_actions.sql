CREATE TABLE tb_incident_actions (
	incident_id int8 NOT NULL,
	actions_id int8 NOT NULL,
	CONSTRAINT uk_fcgwefaugts47223h4f88y0in UNIQUE (actions_id),
	CONSTRAINT fkij60plbonbetn413s69lfwmb7 FOREIGN KEY (actions_id) REFERENCES public.tb_action(id),
	CONSTRAINT fknc2jk5u43j20u442qekp7asb7 FOREIGN KEY (incident_id) REFERENCES public.tb_incident(id)
);