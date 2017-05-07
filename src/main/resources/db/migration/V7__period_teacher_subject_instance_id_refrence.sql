ALTER TABLE period
  ADD teacher_subject_instance_id INT NOT NULL;
ALTER TABLE period
  ADD CONSTRAINT period_teacher_subject_instance_id_fk
  FOREIGN KEY (teacher_subject_instance_id) REFERENCES teacher_subject_instance (id);