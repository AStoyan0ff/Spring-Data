UPDATE developers
SET email = CONCAT(
        LOWER(REPLACE(full_name, ' ', '.')),
        '@placeholder.dev'
            )
WHERE email IS NULL;

UPDATE developers
SET email = LOWER(email);

ALTER TABLE developers
    MODIFY COLUMN email VARCHAR(100) NOT NULL;