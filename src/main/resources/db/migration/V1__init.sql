CREATE TABLE radioisotopes
(
    id              UUID PRIMARY KEY,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP    NOT NULL,

    name            VARCHAR(255) NOT NULL,
    short_name      VARCHAR(255) NOT NULL,
    half_life       INTEGER      NOT NULL,
    half_life_unite VARCHAR(255) NOT NULL,
    aim             VARCHAR(255) NOT NULL
);

CREATE TABLE radiopharmaceuticals
(
    id              UUID PRIMARY KEY,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP    NOT NULL,

    name            VARCHAR(255) NOT NULL,
    short_name      VARCHAR(255) NOT NULL,
    aim             VARCHAR(255) NOT NULL,
    radioisotope_id UUID         NOT NULL,

    CONSTRAINT fk_radioisotope FOREIGN KEY (radioisotope_id) REFERENCES radioisotopes (id)
);

CREATE TABLE cyclotrons
(
    id                 UUID PRIMARY KEY,
    created_at         TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP NOT NULL,

    brand              VARCHAR(255),
    model              VARCHAR(255),
    serial_number      VARCHAR(255),
    beam_energy        NUMERIC(10, 2),
    sources_count      INTEGER,
    exit_ports_count   INTEGER,
    has_water_cooling  BOOLEAN,
    has_helium_cooling BOOLEAN,
    has_air_compressor BOOLEAN
);

CREATE TABLE exit_ports
(
    id           UUID PRIMARY KEY,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL,

    port_number  INTEGER,
    cyclotron_id UUID      NOT NULL,

    CONSTRAINT fk_cyclotron FOREIGN KEY (cyclotron_id) REFERENCES cyclotrons (id)
);

CREATE TABLE targets
(
    id           UUID PRIMARY KEY,
    created_at   TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP NOT NULL,

    name            VARCHAR(255) NOT NULL,
    target_type     VARCHAR(50)  NOT NULL,
    target_pressure NUMERIC(10, 2),
    target_current  NUMERIC(10, 2),
    cyclotron_id    UUID         NOT NULL,
    radioisotope_id UUID         NOT NULL,
    exit_port_id UUID         NOT NULL,

    CONSTRAINT fk_target_cyclotron FOREIGN KEY (cyclotron_id) REFERENCES cyclotrons (id),
    CONSTRAINT fk_target_radioisotope FOREIGN KEY (radioisotope_id) REFERENCES radioisotopes (id),
    CONSTRAINT fk_target_exit_port FOREIGN KEY (exit_port_id) REFERENCES exit_ports (id)
);

CREATE TABLE hot_labs
(
    id                   UUID PRIMARY KEY,
    created_at           TIMESTAMP   NOT NULL,
    updated_at           TIMESTAMP   NOT NULL,

    brand                VARCHAR(255),
    model                VARCHAR(255),
    serial_number        VARCHAR(255),
    clean_classification VARCHAR(50) NOT NULL,
    cabinet_type         VARCHAR(50) NOT NULL
);

CREATE TABLE hot_lab_modules
(
    id            UUID PRIMARY KEY,
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP NOT NULL,

    brand         VARCHAR(255),
    model         VARCHAR(255),
    serial_number VARCHAR(255),
    hot_lab_id    UUID      NOT NULL,
    CONSTRAINT fk_hot_lab FOREIGN KEY (hot_lab_id) REFERENCES hot_labs (id)
);

CREATE TABLE hot_lab_module_types
(
    module_id    UUID        NOT NULL,
    module_types VARCHAR(50) NOT NULL,
    CONSTRAINT fk_module FOREIGN KEY (module_id) REFERENCES hot_lab_modules (id)
);

CREATE TABLE gas_systems
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,

    gas_name   VARCHAR(255) NOT NULL,
    purity     NUMERIC(2, 5)
);

CREATE TABLE gas_systems_used_for
(
    gas_system_id  UUID NOT NULL,
    equipment_type VARCHAR(255),
    CONSTRAINT fk_gas_systems_used_for FOREIGN KEY (gas_system_id) REFERENCES gas_systems (id)
);

