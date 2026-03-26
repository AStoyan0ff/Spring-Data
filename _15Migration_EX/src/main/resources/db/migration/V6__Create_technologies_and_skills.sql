CREATE TABLE technologies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50)
);

CREATE TABLE developers_technologies (
    developer_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,

    PRIMARY KEY (developer_id, technology_id),

        CONSTRAINT fk_dt_developer
        FOREIGN KEY (developer_id) REFERENCES developers(id),

        CONSTRAINT fk_dt_technology
        FOREIGN KEY (technology_id) REFERENCES technologies(id)
);