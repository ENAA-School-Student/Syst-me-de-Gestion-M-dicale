
INSERT INTO patient (nom, prenom, email, telephone, date_naissance)
VALUES('Alami','Mohamed',  'alami.mohamed@gmail.com','0612345678', '1990-05-15'),
      ('Benali','Fatima',   'benali.fatima@gmail.com','0623456789', '1985-08-22'),
      ('Cherkaoui','Youssef',  'cherkaoui.y@gmail.com','0634567890', '1995-03-10'),
      ('Idrissi','Salma',    'idrissi.salma@gmail.com','0645678901', '1988-11-30'),
      ('Tazi','Hassan',   'tazi.hassan@gmail.com','0656789012', '1975-07-04');

INSERT INTO medecin (nom, specialite, email, telephone)
VALUES('Dr. Mansouri', 'Cardiologie','mansouri@clinic.ma','0661234567'),
      ('Dr. Ouali', 'Pédiatrie','ouali@clinic.ma','0672345678'),
      ('Dr. Rachidi','Dermatologie','rachidi@clinic.ma','0683456789'),
      ('Dr. Lahlou','Neurologie','lahlou@clinic.ma','0694567890'),
      ('Dr. Kettani','Médecine Générale','kettani@clinic.ma','0605678901');


INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id)
VALUES('2024-06-10 09:00:00', 'CONFIRME',  1, 1),
      ('2024-06-11 10:30:00', 'PLANIFIE',  2, 2),
      ('2024-06-12 14:00:00', 'TERMINE',   3, 3),
      ('2024-06-13 11:00:00', 'ANNULE',    4, 4),
      ('2024-06-14 08:30:00', 'PLANIFIE',  5, 5),
      ('2024-06-15 16:00:00', 'CONFIRME',  1, 3),
      ('2024-06-16 09:30:00', 'PLANIFIE',  2, 5);

INSERT INTO dossier_medical (diagnostic, observations, date_creation, patient_id)
VALUES ('Hypertension artérielle','Tension élevée, traitement en cours','2024-01-15', 1),
       ('Asthme bronchique', 'Crise légère, inhalateur prescrit','2024-02-20', 2),
       ('Dermatite atopique', 'Éruption cutanée, crème corticoïde prescrite','2024-03-05', 3),
       ('Migraine chronique','Crises fréquentes, suivi neurologique requis','2024-04-10', 4),
       ('Diabète type 2','Glycémie élevée, régime alimentaire conseillé','2024-05-18', 5);