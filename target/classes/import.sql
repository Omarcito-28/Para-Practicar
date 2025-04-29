-- Insertar roles
INSERT INTO roles (rol_name) VALUES ('ADMIN');
INSERT INTO roles (rol_name) VALUES ('DOCENTE');
INSERT INTO roles (rol_name) VALUES ('ESTUDIANTE');

-- Insertar usuarios
INSERT INTO users (name, email, password) VALUES ('Admin User', 'admin@example.com', '$2a$12$lKIirVo.8A3w28m2rNQYdeETJGJk1WI2uO9gdyl4b3aHyUBpaxBj.'); -- contraseña: admin_password
INSERT INTO users (name, email, password) VALUES ('Docente User 1', 'docente1@example.com', '$2a$12$OEv..pgF2hrXUcpQg6QxbOWgId6u/estxLRo/xU9JzDKJWsfWwoQ.'); -- contraseña: docente_password1
INSERT INTO users (name, email, password) VALUES ('Docente User 2', 'docente2@example.com', '$2a$12$Lf70xFf8kvdy2ASh0EY7T.grdNZIAFnDl8fKxv8C2Y36QqyMhby92'); -- contraseña: docente_password2
INSERT INTO users (name, email, password) VALUES ('Estudiante User 1', 'estudiante1@example.com', '$2a$12$QU.GR8X7C/YrFgTmeqTineyViZ/c4.JLcZL4VvGcG6S6YGwSQh4eK'); -- contraseña: estudiante_password1
INSERT INTO users (name, email, password) VALUES ('Estudiante User 2', 'estudiante2@example.com', '$2a$12$7NtSjAAJrl06NJgoWhu9VegsYbpJbIGktEQrthMKJkinvQRjKpz5q'); -- contraseña: estudiante_password2

-- Asignar roles a los usuarios
INSERT INTO users_role (user_id, role_id) VALUES (1, 1); -- Admin con rol ADMIN
INSERT INTO users_role (user_id, role_id) VALUES (2, 2); -- Docente 1 con rol DOCENTE
INSERT INTO users_role (user_id, role_id) VALUES (3, 2); -- Docente 2 con rol DOCENTE
INSERT INTO users_role (user_id, role_id) VALUES (4, 3); -- Estudiante 1 con rol ESTUDIANTE
INSERT INTO users_role (user_id, role_id) VALUES (5, 3); -- Estudiante 2 con rol ESTUDIANTE
