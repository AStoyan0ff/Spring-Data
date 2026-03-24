INSERT INTO people (first_name, last_name, email)
VALUES
    ('Ivan', 'Petrov', 'ivan.petrov@mail.bg'),
    ('Maria', 'Ivanova', 'maria.ivanova@mail.bg'),
    ('Georgi', 'Dimitrov', 'georgi.dimitrov@mail.bg');

INSERT INTO addresses (city, street, postal_code, person_id)
VALUES
    ('Sofia', 'ul. Vitosha 15', '1000', 1),
    ('Plovdiv', 'bul. Maritza 42', '4000', 2),
    ('Sofia', 'ul. Rakovski 100', '1000', 1),
    ('Varna', 'ul. Slivnitsa 8', '9000', 3);