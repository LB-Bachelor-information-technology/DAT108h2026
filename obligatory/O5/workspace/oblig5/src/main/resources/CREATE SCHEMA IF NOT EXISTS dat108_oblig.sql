CREATE SCHEMA IF NOT EXISTS dat108_oblig5_h26;
SET search_path TO dat108_oblig5_h26;

CREATE TABLE deltager (
    mobil CHARACTER(8) PRIMARY KEY,
    passordhash CHARACTER(64) NOT NULL,
    fornavn CHARACTER VARYING(40),
    etternavn CHARACTER VARYING(40),
    kjonn CHARACTER VARYING(6)
);