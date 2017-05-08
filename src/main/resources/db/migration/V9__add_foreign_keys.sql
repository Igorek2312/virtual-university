ALTER TABLE account_role
ADD CONSTRAINT account_role_account_id_fk
FOREIGN KEY (account_id) REFERENCES account (id);
ALTER TABLE account_role
ADD CONSTRAINT account_role_role_id_fk
FOREIGN KEY (role_id) REFERENCES role (id);