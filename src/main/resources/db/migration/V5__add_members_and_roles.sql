CREATE TABLE members
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,

    name       VARCHAR(255) NOT NULL,
    sure_name  VARCHAR(255),
    phone      VARCHAR(255),
    email      VARCHAR(255),
    position   VARCHAR(255)
);

CREATE TABLE roles
(
    id             UUID PRIMARY KEY,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL,

    name           VARCHAR(255) NOT NULL UNIQUE,

    view_users     BOOLEAN,
    edit_users     BOOLEAN,
    create_users   BOOLEAN,
    delete_users   BOOLEAN,

    view_roles     BOOLEAN,
    edit_roles     BOOLEAN,
    create_roles   BOOLEAN,
    delete_roles   BOOLEAN,

    view_company   BOOLEAN,
    edit_company   BOOLEAN,
    create_company BOOLEAN,
    delete_company BOOLEAN
);

CREATE TABLE member_roles
(
    id        UUID PRIMARY KEY,
    member_id UUID NOT NULL,
    role_id   UUID NOT NULL,

    CONSTRAINT fk_member FOREIGN KEY (member_id)
        REFERENCES members (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES roles (id)
        ON DELETE CASCADE
);