CREATE TABLE company
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,

    name        VARCHAR(255) NOT NULL,
    short_name  VARCHAR(255),
    address     VARCHAR(255),
    phone_number VARCHAR(255),
    email       VARCHAR(255),
    website     VARCHAR(255),
    logo_url     VARCHAR(255)

);