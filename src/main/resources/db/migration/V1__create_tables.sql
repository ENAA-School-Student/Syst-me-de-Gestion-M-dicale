
CREATE TABLE IF NOT EXISTS patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) ,
    prenom VARCHAR(255),
    email VARCHAR(255),
    telephone VARCHAR(10) ,
    date_naissance DATE
    );

CREATE TABLE IF NOT EXISTS medecin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) ,
    specialite VARCHAR(255),
    email  VARCHAR(255) ,
    telephone  VARCHAR(10)
    );

CREATE TABLE IF NOT EXISTS rendez_vous (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_rendez_vous DATE,
    statut VARCHAR(50),
    patient_id BIGINT,
    medecin_id BIGINT,
    CONSTRAINT fk_rendez_vous_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_rendez_vous_medecin  FOREIGN KEY (medecin_id) REFERENCES medecin(id)
    );

CREATE TABLE IF NOT EXISTS dossier_medical (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    diagnostic  VARCHAR(255) NOT NULL,
    observations  VARCHAR(255) NOT NULL,
    date_creation DATE ,
    patient_id  BIGINT,
    CONSTRAINT fk_dossier_medical_patient FOREIGN KEY (patient_id) REFERENCES patient(id)

    );