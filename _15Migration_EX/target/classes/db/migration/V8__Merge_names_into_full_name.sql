ALTER TABLE developers
    ADD COLUMN full_name VARCHAR(100);

-- попълваме я
UPDATE developers
SET full_name = CONCAT(first_name, ' ', last_name)
WHERE full_name IS NULL;

-- правим я NOT NULL
ALTER TABLE developers
    MODIFY COLUMN full_name VARCHAR(100) NOT NULL;

-- махаме старите колони (само ако съществуват)
ALTER TABLE developers DROP COLUMN first_name;
ALTER TABLE developers DROP COLUMN last_name;