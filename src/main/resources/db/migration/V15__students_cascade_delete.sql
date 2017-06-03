ALTER TABLE student DROP FOREIGN KEY FKoootcgotavmpat2yv9o52wx1q;
ALTER TABLE student
ADD CONSTRAINT FKoootcgotavmpat2yv9o52wx1q
FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE CASCADE;