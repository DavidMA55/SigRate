INSERT INTO degree (id, name) VALUES (1, 'Ingenieria Informatica') ON CONFLICT (id) DO NOTHING;;
INSERT INTO degree (id, name) VALUES (2, 'Ingenieria Quimica') ON CONFLICT (id) DO NOTHING;;

INSERT INTO signature (id, year, degree_id) VALUES ('ES', 3, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('BD', 2, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('PROG1', 1, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('PROG2', 1, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('SO', 2, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('REDES', 3, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO signature (id, year, degree_id) VALUES ('IA', 4, 1) ON CONFLICT (id) DO NOTHING;
