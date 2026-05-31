INSERT INTO users (id, email, username, password,role)
VALUES  (1, 'admin@healthcare.com', 'admin_principal', '12345', 'ADMIN'),
        (2, 'dr.alami@healthcare.com', 'dr_alami', '12345', 'MEDECIN'),
        (3, 'dr.benjelloun@healthcare.com', 'dr_benjelloun', '12345',  'MEDECIN'),
        (4, 'amrani.yassine@gmail.com', 'yassine_amrani', '12345', 'PATIENT'),
        (5, 'naji.fatima@gmail.com', 'fatima_naji', '12345',  'PATIENT');

INSERT INTO medecin (id, telephone, specialite)
VALUES (2, '0611223344','Cardiologie'),
       (3, '0655667788','Pédiatrie');

INSERT INTO patient (id, prenom,telephone, date_naissance)
VALUES (4, 'Yassine','0666778899', '1995-06-15'),
       (5, 'Fatima', '0622334455', '1988-11-23');


INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id)
VALUES ('2026-06-01', 'CONFIRME', 4, 2),
       ('2026-06-02', 'PLANIFIE', 5, 3),
       ('2026-05-20', 'TERMINE', 4, 3);

INSERT INTO dossier_medical (diagnostic, observations, date_creation, patient_id)
VALUES ('Hypertension artérielle légère', 'Patient doit réduire le sel et faire du sport. Suivi dans 3 mois.', '2026-05-20', 4),
       ('Grippe saisonnière', 'Repos de 3 jours prescrit avec paracétamol.', '2026-05-22', 5);