CREATE TABLE company_licenses
(
    id                      UUID PRIMARY KEY,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL,

    name                    VARCHAR(255) NOT NULL,
    description             VARCHAR(255),
    organization_issued     VARCHAR(255),
    date                    TIMESTAMP,
    date_of_expiry          TIMESTAMP,
    expiry_reminder         VARCHAR(255),
    training_duration_value INTEGER,
    training_duration_unit  VARCHAR(255),
    active                  BOOLEAN
);

CREATE TABLE company_license_files
(
    license_id UUID         NOT NULL,
    file_url   VARCHAR(255) NOT NULL,
    CONSTRAINT fk_company_licenses
        FOREIGN KEY (license_id)
            REFERENCES company_licenses (id)
            ON DELETE CASCADE
);

CREATE INDEX idx_company_license_files_license_id
    ON company_license_files (license_id);