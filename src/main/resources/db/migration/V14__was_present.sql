ALTER TABLE grade ADD was_present BOOL DEFAULT FALSE  NOT NULL;
ALTER TABLE grade MODIFY value TINYINT(4);