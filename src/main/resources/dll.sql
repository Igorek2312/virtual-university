create table account
(
	id int not null auto_increment
		primary key,
	document_number varchar(255) null,
	enabled bit null,
	first_name varchar(255) null,
	last_name varchar(255) null,
	middle_name varchar(255) null,
	password varchar(255) null,
	username varchar(255) null
)
;

create table account_role
(
	account_id int not null,
	role_id int not null
)
;

create table classroom
(
	id int not null auto_increment
		primary key,
	name varchar(255) null
)
;

create table faculty
(
	id int not null auto_increment
		primary key,
	name varchar(255) null
)
;

create table grade
(
	id int not null auto_increment
		primary key,
	value tinyint null,
	period_instance_id int not null
)
;

create index grade_period_instance_id_fk
	on grade (period_instance_id)
;

create table `group`
(
	id int not null auto_increment
		primary key,
	name varchar(255) null,
	year_entered year null,
	year_of_study_entered int null,
	specialty_id int not null
)
;

create index group_specialty_id_fk
	on `group` (specialty_id)
;

create table period
(
	id int not null auto_increment
		primary key,
	day_of_week tinyint null,
	default_type varchar(255) null,
	odd_even varchar(255) null,
	period_number tinyint null,
	classroom_id int not null,
	subject_instance_id int not null,
	teacher_id int not null,
	group_id int not null,
	constraint FK6lhdvthnag091lpyo0jwd6grg
	foreign key (classroom_id) references `virtual-university`.classroom (id),
	constraint period_group_id_fk
	foreign key (group_id) references `virtual-university`.`group` (id)
)
;

create index FK6lhdvthnag091lpyo0jwd6grg
	on period (classroom_id)
;

create index FKh31pwc5kay6v8uhl7qao3bcvb
	on period (teacher_id)
;

create index FKhtmo9ni7oc6x6rpe17jago4x7
	on period (subject_instance_id)
;

create index period_group_id_fk
	on period (group_id)
;

create table period_instance
(
	id int not null auto_increment
		primary key,
	date date null,
	type varchar(255) null,
	period_id int not null,
	constraint period_instance_period_id_fk
	foreign key (period_id) references `virtual-university`.period (id)
)
;

create index period_instance_period_id_fk
	on period_instance (period_id)
;

alter table grade
	add constraint grade_period_instance_id_fk
foreign key (period_instance_id) references `virtual-university`.period_instance (id)
;

create table role
(
	id int not null auto_increment
		primary key,
	name varchar(255) not null
)
;

create table specialty
(
	id int not null auto_increment
		primary key,
	name varchar(255) null,
	faculty_id int not null,
	constraint specialty_faculty_id_fk
	foreign key (faculty_id) references `virtual-university`.faculty (id)
)
;

create index specialty_faculty_id_fk
	on specialty (faculty_id)
;

alter table `group`
	add constraint group_specialty_id_fk
foreign key (specialty_id) references `virtual-university`.specialty (id)
;

create table student
(
	id int not null auto_increment
		primary key,
	finance_type varchar(255) null,
	record_book_number varchar(255) null,
	account_id int not null,
	group_id int not null,
	constraint FKoootcgotavmpat2yv9o52wx1q
	foreign key (account_id) references `virtual-university`.account (id),
	constraint student_group_id_fk
	foreign key (group_id) references `virtual-university`.`group` (id)
)
;

create index FKoootcgotavmpat2yv9o52wx1q
	on student (account_id)
;

create index student_group_id_fk
	on student (group_id)
;

create table subject
(
	id int not null auto_increment
		primary key,
	department varchar(255) null,
	name varchar(255) null
)
;

create table subject_instance
(
	id int not null auto_increment
		primary key,
	control_type varchar(255) null,
	date_begin date null,
	date_end date null,
	hours int null,
	subject_type varchar(255) null,
	subject_id int not null,
	constraint FKi47ad5g9252gwwet50l22jvu1
	foreign key (subject_id) references `virtual-university`.subject (id)
)
;

create index FKi47ad5g9252gwwet50l22jvu1
	on subject_instance (subject_id)
;

alter table period
	add constraint FKhtmo9ni7oc6x6rpe17jago4x7
foreign key (subject_instance_id) references `virtual-university`.subject_instance (id)
;

create table teacher
(
	id int not null auto_increment
		primary key,
	account_id int null,
	constraint FK5t4vdu18ohx39bj4lef9qf779
	foreign key (account_id) references `virtual-university`.account (id)
)
;

create index FK5t4vdu18ohx39bj4lef9qf779
	on teacher (account_id)
;

alter table period
	add constraint FKh31pwc5kay6v8uhl7qao3bcvb
foreign key (teacher_id) references `virtual-university`.teacher (id)
;


/**old**/
create table account
(
	id int not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	middle_name varchar(255) not null,
	username varchar(25) null,
	password varchar(25) null,
	role_name varchar(255) not null,
	enabled bit null,
	document_number varchar(255) not null,
	constraint `PRIMARY`
		primary key (id),
	constraint account_username_uindex
		unique (username),
	constraint account_passport_number_uindex
		unique (document_number)
)
;

create table classroom
(
	id int not null,
	name varchar(10) not null,
	constraint `PRIMARY`
		primary key (id)
)
;

create table faculty
(
	id int not null,
	name varchar(255) not null,
	constraint `PRIMARY`
		primary key (id),
	constraint faculty_name_uindex
		unique (name)
)
;

create table grade
(
	id int not null,
	value tinyint not null,
	date date not null,
	student_id int not null,
	period_instance_id int not null,
	constraint `PRIMARY`
		primary key (id)
)
;

create index grade_student_id_fk
	on grade (student_id)
;

create index grade_period_instance_id_fk
	on grade (period_instance_id)
;

create table `group`
(
	id int not null,
	name varchar(255) not null,
	year_entered year not null,
	year_of_study_entered int default '1' not null,
	specialty_id int not null,
	constraint `PRIMARY`
		primary key (id)
)
;

create index group_specialty_id_fk
	on `group` (specialty_id)
;

create table period
(
	id int not null,
	day_of_week tinyint not null,
	group_id int not null,
	teacher_id int not null,
	odd_even varchar(4) not null,
	classroom_id int not null,
	period_number tinyint null,
	subject_instance_id int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint period_group_id_fk
		foreign key (group_id) references `group` (id),
	constraint period_classroom_id_fk
		foreign key (classroom_id) references classroom (id)
)
;

create index period_group_id_fk
	on period (group_id)
;

create index period_teacher_id_fk
	on period (teacher_id)
;

create index period_classroom_id_fk
	on period (classroom_id)
;

create index period_subject_instance_id_fk
	on period (subject_instance_id)
;

create table period_instance
(
	id int not null,
	type varchar(255) not null,
	date date not null,
	period_id int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint period_instance_period_id_fk
		foreign key (period_id) references period (id)
)
;

create index period_instance_period_id_fk
	on period_instance (period_id)
;

alter table grade
	add constraint grade_period_instance_id_fk
		foreign key (period_instance_id) references period_instance (id)
;

create table specialty
(
	id int not null,
	name varchar(255) not null,
	faculty_id int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint specialty_name_uindex
		unique (name),
	constraint specialty_faculty_id_fk
		foreign key (faculty_id) references faculty (id)
)
;

create index specialty_faculty_id_fk
	on specialty (faculty_id)
;

alter table `group`
	add constraint group_specialty_id_fk
		foreign key (specialty_id) references specialty (id)
;

create table student
(
	id int not null,
	group_id int not null,
	account_id int not null,
	finance_type varchar(255) not null,
	record_book_number int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint student_group_id_fk
		foreign key (group_id) references `group` (id),
	constraint student_account_id_fk
		foreign key (account_id) references account (id)
)
;

create index student_group_id_fk
	on student (group_id)
;

create index student_account_id_fk
	on student (account_id)
;

alter table grade
	add constraint grade_student_id_fk
		foreign key (student_id) references student (id)
;

create table subject
(
	id int not null,
	name varchar(255) not null,
	department varchar(255) not null,
	constraint `PRIMARY`
		primary key (id)
)
;

create table subject_instance
(
	id int not null,
	subject_id int not null,
	controll_type varchar(255) not null,
	subject_type varchar(255) not null,
	date_start date not null,
	date_finish date not null,
	hours int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint subject_instance_subject_id_fk
		foreign key (subject_id) references subject (id)
)
;

create index subject_instance_subject_id_fk
	on subject_instance (subject_id)
;

alter table period
	add constraint period_subject_instance_id_fk
		foreign key (subject_instance_id) references subject_instance (id)
;

create table teacher
(
	id int not null,
	account_id int not null,
	constraint `PRIMARY`
		primary key (id),
	constraint teacher_account_id_fk
		foreign key (account_id) references account (id)
)
;

create index teacher_account_id_fk
	on teacher (account_id)
;

alter table period
	add constraint period_teacher_id_fk
		foreign key (teacher_id) references teacher (id)
;


