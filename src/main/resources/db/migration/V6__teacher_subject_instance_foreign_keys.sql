ALTER TABLE teacher_subject_instance
ADD CONSTRAINT teacher_subject_instance_teacher_id_fk
FOREIGN KEY (teacher_id) REFERENCES teacher (id);
ALTER TABLE teacher_subject_instance
ADD CONSTRAINT teacher_subject_instance_subject_instance_id_fk
FOREIGN KEY (subject_instance_id) REFERENCES subject_instance (id);