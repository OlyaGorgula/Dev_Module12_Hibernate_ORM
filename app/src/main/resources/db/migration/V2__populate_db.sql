INSERT INTO client (name) VALUES
('Ivan Vasiliy'),
('Olga Gorgula'),
('Taras Sergiev'),
('Anyuta Stepan'),
('Apache Anny'),
('Karson Jo'),
('Mini Miki'),
('Tusso Toni'),
('Avatar Ava'),
('Bodya Toshka');

INSERT INTO planet (planet_id, name) VALUES
('MARS', 'Mars'),
('VENERA', 'Venera'),
('JUPITER', 'Jupiter'),
('URANUS', 'Uranus'),
('MERCURY', 'Mercury');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
('2000-01-07 11:20:00', '1', 'MARS', 'JUPITER'),
('2000-01-07 11:20:00', '2', 'MARS', 'VENERA'),
('2000-01-07 12:20:00', '3', 'MARS', 'JUPITER'),
('2000-01-07 11:30:00', '4', 'MARS', 'JUPITER'),
('2000-02-07 11:20:00', '5', 'URANUS', 'JUPITER'),
('2000-03-07 23:20:00', '6', 'URANUS', 'MERCURY'),
('2000-04-07 23:20:00', '7', 'JUPITER', 'URANUS'),
('2000-04-07 23:20:00', '8', 'VENERA', 'JUPITER'),
('2000-05-07 23:20:00', '9', 'URANUS', 'MERCURY'),
('2000-01-07 23:20:00', '10', 'VENERA', 'MERCURY');