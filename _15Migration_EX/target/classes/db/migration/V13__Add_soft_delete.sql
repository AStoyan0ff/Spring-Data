ALTER TABLE developers
    ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE projects
    ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;

UPDATE developers
    SET is_active = FALSE
WHERE id = 5;

UPDATE projects
    SET is_active = FALSE
WHERE developer_id = 5;