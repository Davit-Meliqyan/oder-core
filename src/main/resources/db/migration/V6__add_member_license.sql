CREATE TABLE member_licenses
(
    id                      UUID PRIMARY KEY,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL,

    member_id               UUID         NOT NULL,
    name                    VARCHAR(255) NOT NULL,
    description             TEXT,
    organization_issued     VARCHAR(255),
    date                    TIMESTAMP,
    date_of_expiry          TIMESTAMP,
    expiry_reminder         VARCHAR(50),
    training_duration_value INTEGER,
    training_duration_unit  VARCHAR(50),
    active                  BOOLEAN,

    CONSTRAINT fk_member_license_member FOREIGN KEY (member_id)
        REFERENCES members (id)
        ON DELETE CASCADE
);

CREATE TABLE member_license_files
(
    license_id UUID NOT NULL,
    file_url   VARCHAR(1000),

    CONSTRAINT fk_member_license FOREIGN KEY (license_id)
        REFERENCES member_licenses (id)
        ON DELETE CASCADE
);