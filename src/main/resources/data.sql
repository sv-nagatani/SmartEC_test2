INSERT INTO roles(id, name) VALUES(1, 'ROLE_GENERAL');
INSERT INTO roles(id, name) VALUES(2, 'ROLE_ADMIN');

-- password = "user"
INSERT INTO login_user(id, name, dsp_name, password) VALUES(1, 'user', '一般太郎', '$2a$10$FvrByODLjpgMf8C826bmkuH9XGn8jGGN7sLUoErNpWlPjmIjg2XDO');
-- password = "admin"
INSERT INTO login_user(id, name, dsp_name, password) VALUES(2, 'admin', '管理太郎', '$2a$10$SJTWvNl16fCU7DaXtWC0DeN/A8IOakpCkWWNZ/FKRV2CHvWElQwMS');

INSERT INTO user_role(user_id, role_id) VALUES(1, 1);
INSERT INTO user_role(user_id, role_id) VALUES(2, 1);
INSERT INTO user_role(user_id, role_id) VALUES(2, 2);
