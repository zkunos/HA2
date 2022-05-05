DROP TABLE USERS;

CREATE TABLE USERS
(
    EMAIL    varchar(255) NOT NULL,
    PASSWORD varchar(255) NOT NULL,
    NAME     varchar(255),
    SURNAME  varchar(255),
    PRIMARY KEY (EMAIL)
);
