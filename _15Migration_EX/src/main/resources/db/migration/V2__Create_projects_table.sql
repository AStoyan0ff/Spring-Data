CREATE TABLE projects (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    status VARCHAR(30) NOT NULL DEFAULT 'NOT_STARTED',
    developer_id BIGINT,

    CONSTRAINT fk_project_developer
    FOREIGN KEY (developer_id) REFERENCES developers(id)
);