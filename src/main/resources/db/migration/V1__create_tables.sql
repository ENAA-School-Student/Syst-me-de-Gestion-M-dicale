
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telephone VARCHAR(10)  NOT NULL,
    date_naissance DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS medecin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    specialite VARCHAR(255) NOT NULL,
    email  VARCHAR(255) NOT NULL UNIQUE,
    telephone  VARCHAR(10)  NOT NULL
    );

CREATE TABLE IF NOT EXISTS rendez_vous (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_rendez_vous DATETIME     NOT NULL,
    statut VARCHAR(50)  NOT NULL,
    patient_id BIGINT,
    medecin_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (medecin_id) REFERENCES medecin(id)
    );

CREATE TABLE IF NOT EXISTS dossier_medical (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    diagnostic  VARCHAR(255) NOT NULL,
    observations  VARCHAR(255) NOT NULL,
    date_creation DATE NOT NULL,
    patient_id  BIGINT UNIQUE,
    medecin_id BIGINT,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (medecin_id) REFERENCES medecin(id)
    );