ALTER TABLE grade ADD student_id INT NOT NULL;
ALTER TABLE grade
ADD CONSTRAINT grade_student_id_fk
FOREIGN KEY (student_id) REFERENCES student (id);