INSERT INTO tb_user (email, "name", "password", username)
VALUES('admin@email.com', 'ADMIN', '$2a$10$kLGloBxdEUcb.u8ea45LUO6yjW1FqBOvamLlzrzeWlOsdAGHCvJ/q', 'admin');

INSERT INTO tb_users_roles (user_id, role_id)
VALUES(1, 3);
