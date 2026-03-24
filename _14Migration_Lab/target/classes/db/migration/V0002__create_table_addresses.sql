CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    street VARCHAR(200),
    postal_code VARCHAR(10),
    person_id BIGINT,
        CONSTRAINT fk_address_person
        FOREIGN KEY (person_id)
        REFERENCES people(id)
);