
DROP TABLE IF EXISTS dossier_medical;
DROP TABLE IF EXISTS rendez_vous;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS medecin;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     email VARCHAR(255) UNIQUE NOT NULL,
     username VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     role VARCHAR(50) NOT NULL
);

CREATE TABLE patient (
     id BIGINT PRIMARY KEY,
     prenom VARCHAR(255),
     telephone VARCHAR(20),
     date_naissance DATE,
     CONSTRAINT fk_patient_user FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE medecin (
     id BIGINT PRIMARY KEY,
     telephone VARCHAR(20),
     specialite VARCHAR(255),
     CONSTRAINT fk_medecin_user FOREIGN KEY (id)  REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE rendez_vous (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     date_rendez_vous DATE,
     statut VARCHAR(50),
     patient_id BIGINT,
     medecin_id BIGINT,
     CONSTRAINT fk_rendez_vous_patient FOREIGN KEY (patient_id)  REFERENCES patient(id),
     CONSTRAINT fk_rendez_vous_medecin  FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);


CREATE TABLE dossier_medical (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      diagnostic VARCHAR(255) NOT NULL,
      observations VARCHAR(255) NOT NULL,
      date_creation DATE,
      patient_id BIGINT UNIQUE,
      CONSTRAINT fk_dossier_medical_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);