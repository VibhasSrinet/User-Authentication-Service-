ALTER TABLE userservice.`role`
    ADD is_deleted BIT(1) NULL;

ALTER TABLE userservice.`role`
    MODIFY is_deleted BIT (1) NOT NULL;

ALTER TABLE userservice.token
    ADD is_deleted BIT(1) NULL;

ALTER TABLE userservice.token
    MODIFY is_deleted BIT (1) NOT NULL;

ALTER TABLE userservice.user
    ADD is_deleted BIT(1) NULL;

ALTER TABLE userservice.user
    MODIFY is_deleted BIT (1) NOT NULL;