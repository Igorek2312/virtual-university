CREATE TABLE teacher_subject_instance
(
  id                  INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  subject_instance_id INT          NOT NULL,
  teacher_id          INT          NOT NULL,
  period_type         VARCHAR(255) NOT NULL
);

