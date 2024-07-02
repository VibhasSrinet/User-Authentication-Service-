ALTER TABLE userservice.`role`
    ADD role_type VARCHAR(255) NULL;

ALTER TABLE userservice.`role`
DROP
COLUMN name;