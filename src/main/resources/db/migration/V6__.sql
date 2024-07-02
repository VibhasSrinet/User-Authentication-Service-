ALTER TABLE `role`
    ADD name VARCHAR(255) NULL;

ALTER TABLE `role`
DROP
COLUMN role_type;

ALTER TABLE authorization
    MODIFY access_token_type VARCHAR (255);

ALTER TABLE authorization_consent
    MODIFY authorities VARCHAR (1000);

ALTER TABLE authorization
    MODIFY authorization_code_metadata VARCHAR (255);

ALTER TABLE authorization
    MODIFY authorization_grant_type VARCHAR (255);

ALTER TABLE client
    MODIFY authorization_grant_types VARCHAR (1000);

ALTER TABLE client
    MODIFY client_authentication_methods VARCHAR (1000);

ALTER TABLE client
    MODIFY client_id VARCHAR (255);

ALTER TABLE client
    MODIFY client_name VARCHAR (255);

ALTER TABLE client
    MODIFY client_secret VARCHAR (255);

ALTER TABLE client
    MODIFY client_settings VARCHAR (2000);

ALTER TABLE client
    MODIFY post_logout_redirect_uris VARCHAR (1000);

ALTER TABLE client
    MODIFY redirect_uris VARCHAR (1000);

ALTER TABLE authorization
    MODIFY registered_client_id VARCHAR (255);

ALTER TABLE client
    MODIFY scopes VARCHAR (1000);

ALTER TABLE client
    MODIFY token_settings VARCHAR (2000);