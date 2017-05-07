CREATE TABLE account
(
  id              INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  document_number VARCHAR(255) NULL,
  enabled         BIT          NULL,
  first_name      VARCHAR(255) NULL,
  last_name       VARCHAR(255) NULL,
  middle_name     VARCHAR(255) NULL,
  password        VARCHAR(255) NULL,
  username        VARCHAR(255) NULL
);

CREATE TABLE role
(
  id   INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE account_role
(
  account_id INT NOT NULL,
  role_id    INT NOT NULL
);

CREATE TABLE faculty
(
  id   INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NULL
);

CREATE TABLE specialty
(
  id         INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name       VARCHAR(255) NULL,
  faculty_id INT          NOT NULL,
  CONSTRAINT specialty_faculty_id_fk
  FOREIGN KEY (faculty_id) REFERENCES `virtual-university`.faculty (id)
);

CREATE INDEX specialty_faculty_id_fk
  ON specialty (faculty_id);

CREATE TABLE `group`
(
  id                    INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name                  VARCHAR(255) NULL,
  year_entered          YEAR         NULL,
  year_of_study_entered INT          NULL,
  specialty_id          INT          NOT NULL,
  CONSTRAINT group_specialty_id_fk
  FOREIGN KEY (specialty_id) REFERENCES `virtual-university`.specialty (id)
);

CREATE INDEX group_specialty_id_fk
  ON `group` (specialty_id);

CREATE TABLE student
(
  id                 INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  finance_type       VARCHAR(255) NULL,
  record_book_number VARCHAR(255) NULL,
  account_id         INT          NOT NULL,
  group_id           INT          NOT NULL,
  CONSTRAINT FKoootcgotavmpat2yv9o52wx1q
  FOREIGN KEY (account_id) REFERENCES `virtual-university`.account (id),
  CONSTRAINT student_group_id_fk
  FOREIGN KEY (group_id) REFERENCES `virtual-university`.`group` (id)
);

CREATE INDEX FKoootcgotavmpat2yv9o52wx1q
  ON student (account_id);

CREATE INDEX student_group_id_fk
  ON student (group_id);

CREATE TABLE teacher
(
  id         INT NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  account_id INT NULL,
  CONSTRAINT FK5t4vdu18ohx39bj4lef9qf779
  FOREIGN KEY (account_id) REFERENCES `virtual-university`.account (id)
);

CREATE INDEX FK5t4vdu18ohx39bj4lef9qf779
  ON teacher (account_id);

CREATE TABLE subject
(
  id         INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  department VARCHAR(255) NULL,
  name       VARCHAR(255) NULL
);

CREATE TABLE subject_instance
(
  id           INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  control_type VARCHAR(255) NULL,
  date_begin   DATE         NULL,
  date_end     DATE         NULL,
  hours        INT          NULL,
  subject_type VARCHAR(255) NULL,
  subject_id   INT          NOT NULL,
  CONSTRAINT FKi47ad5g9252gwwet50l22jvu1
  FOREIGN KEY (subject_id) REFERENCES `virtual-university`.subject (id)
);

CREATE INDEX FKi47ad5g9252gwwet50l22jvu1
  ON subject_instance (subject_id);

CREATE TABLE classroom
(
  id   INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(255) NULL
);

CREATE TABLE period
(
  id                  INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  day_of_week         TINYINT      NULL,
  default_type        VARCHAR(255) NULL,
  odd_even            VARCHAR(255) NULL,
  period_number       TINYINT      NULL,
  classroom_id        INT          NOT NULL,
  subject_instance_id INT          NOT NULL,
  teacher_id          INT          NOT NULL,
  group_id            INT          NOT NULL,
  CONSTRAINT FK6lhdvthnag091lpyo0jwd6grg
  FOREIGN KEY (classroom_id) REFERENCES `virtual-university`.classroom (id),
  CONSTRAINT FKhtmo9ni7oc6x6rpe17jago4x7
  FOREIGN KEY (subject_instance_id) REFERENCES `virtual-university`.subject_instance (id),
  CONSTRAINT FKh31pwc5kay6v8uhl7qao3bcvb
  FOREIGN KEY (teacher_id) REFERENCES `virtual-university`.teacher (id),
  CONSTRAINT period_group_id_fk
  FOREIGN KEY (group_id) REFERENCES `virtual-university`.`group` (id)
);

CREATE INDEX FK6lhdvthnag091lpyo0jwd6grg
  ON period (classroom_id);

CREATE INDEX FKh31pwc5kay6v8uhl7qao3bcvb
  ON period (teacher_id);

CREATE INDEX FKhtmo9ni7oc6x6rpe17jago4x7
  ON period (subject_instance_id);

CREATE INDEX period_group_id_fk
  ON period (group_id);

CREATE TABLE period_instance
(
  id        INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  date      DATE         NULL,
  type      VARCHAR(255) NULL,
  period_id INT          NOT NULL,
  CONSTRAINT period_instance_period_id_fk
  FOREIGN KEY (period_id) REFERENCES `virtual-university`.period (id)
);

CREATE INDEX period_instance_period_id_fk
  ON period_instance (period_id);

CREATE TABLE grade
(
  id                 INT     NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  value              TINYINT NULL,
  period_instance_id INT     NOT NULL,
  CONSTRAINT grade_period_instance_id_fk
  FOREIGN KEY (period_instance_id) REFERENCES `virtual-university`.period_instance (id)
);

CREATE INDEX grade_period_instance_id_fk
  ON grade (period_instance_id);






