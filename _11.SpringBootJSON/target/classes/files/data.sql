-- Addresses
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (1, 'Bulgaria', 'Sofia', 'Vitosha Blvd 15', '1000');
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (2, 'Bulgaria', 'Plovdiv', 'Main Street 42', '4000');
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (3, 'Bulgaria', 'Varna', 'Sea Garden 7', '9000');
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (4, 'Bulgaria', 'Sofia', 'Mladost 4 Block 456', '1712');
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (5, 'Spain', 'Barcelona', 'La Rambla 100', '08002');
INSERT INTO addresses (id, country, city, street, zip_code) VALUES (6, 'Germany', 'Berlin', 'Unter den Linden 5', '10117');

-- People
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (1, 'Petar', 'Ivanov', 'petar.ivanov@mail.bg', 28, 1);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (2, 'Maria', 'Petrova', 'maria.petrova@mail.bg', 34, 1);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (3, 'Georgi', 'Dimitrov', 'georgi.d@mail.bg', 45, 2);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (4, 'Elena', 'Koleva', 'elena.k@mail.bg', 22, 3);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (5, 'Ivan', 'Todorov', 'ivan.t@mail.bg', 31, 4);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (6, 'Sofia', 'Georgieva', 'sofia.g@mail.bg', 27, 5);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (7, 'Dimitar', 'Stoyanov', 'dimitar.s@mail.bg', 39, 6);
INSERT INTO people (id, first_name, last_name, email, age, address_id) VALUES (8, 'Anna', 'Marinova', 'anna.m@mail.bg', 25, NULL);
