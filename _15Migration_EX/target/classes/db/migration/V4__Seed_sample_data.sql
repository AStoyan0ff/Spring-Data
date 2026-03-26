INSERT INTO developers (first_name, last_name, email)
VALUES

    ('Ivan', 'Petrov', 'ivan.petrov@devs.bg'),
    ('Maria', 'Ivanova', 'maria.ivanova@devs.bg'),
    ('Georgi', 'Dimitrov', NULL),
    ('Elena', 'Todorova', 'elena.t@devs.bg'),
    ('Nikolay', 'Stoyanov', NULL);

INSERT INTO projects (name, description, status, developer_id)
VALUES
    ('E-Shop API', 'REST API for online store', 'IN_PROGRESS', 1),
    ('Blog Platform', 'Markdown-based blog engine', 'COMPLETED', 2),
    ('Chat App', 'Real-time messaging with WebSocket', 'IN_PROGRESS', 1),
    ('Task Tracker', 'Kanban-style project management', 'NOT_STARTED', 3),
    ('Weather Service', 'Weather data microservice', 'IN_PROGRESS', 4),
    ('Portfolio Site', 'Personal developer portfolio', 'COMPLETED', 5);