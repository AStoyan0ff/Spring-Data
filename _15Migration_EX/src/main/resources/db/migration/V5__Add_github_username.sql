ALTER TABLE developers ADD COLUMN github_username VARCHAR(50);

UPDATE developers
SET github_username = CONCAT(
        LOWER(first_name), '-', LOWER(last_name)
                      )
WHERE github_username IS NULL;

ALTER TABLE developers
    MODIFY COLUMN github_username VARCHAR(50) NOT NULL;