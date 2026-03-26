ALTER TABLE projects ADD COLUMN max_team_size INT DEFAULT 5;

UPDATE projects SET max_team_size = 3 WHERE status = 'NOT_STARTED';
UPDATE projects SET max_team_size = 8 WHERE status = 'IN_PROGRESS';
UPDATE projects SET max_team_size = 5 WHERE status = 'COMPLETED';