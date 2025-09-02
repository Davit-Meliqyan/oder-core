ALTER TABLE cyclotrons
DROP COLUMN has_water_cooling,
    DROP COLUMN has_helium_cooling,
    DROP COLUMN has_air_compressor,
    ADD COLUMN name VARCHAR(255);