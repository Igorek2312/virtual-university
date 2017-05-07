ALTER TABLE subject_instance
  ADD group_id INT NOT NULL;
ALTER TABLE subject_instance
  ADD CONSTRAINT subject_instance_group_id_fk
FOREIGN KEY (group_id) REFERENCES `group` (id);