package org.example.healthcare.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.example.healthcare.repository.DossierMedicalRepository;
import org.example.healthcare.repository.MedecinRepository;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {
    private  final DossierMedicalRepository dossierMedicalRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    private  final MedecinRepository medecinRepository;

    public byte[] generateDossierPdf(Long id) {
        DossierMedicalEntity dossier =dossierMedicalRepository.findById(id).orElseThrow(() -> new RuntimeException("Dossier introuvable"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("DOSSIER MEDICAL"));
        document.add(new Paragraph("Diagnostic : " + dossier.getDiagnostic()));
        document.add(new Paragraph("Observations : " + dossier.getObservations()));
        document.add(new Paragraph("Date : " + dossier.getDateCreation()));
        document.close();
        return out.toByteArray();
    }

    public byte[] generateRendezVousPdf(Long patientId) {
        List<RendezVousEntity> rdvs = rendezVousRepository.findByPatientId(patientId);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("LISTE DES RENDEZ-VOUS"));
        if (rdvs.isEmpty()){
              document.add(new Paragraph("list des rendez vous est vide"));
        }

        for (RendezVousEntity rdv : rdvs) {
            document.add(new Paragraph("Date : " + rdv.getDateRendezVous() + " | Statut : " + rdv.getStatut()
            ));
        }
        document.close();
        return out.toByteArray();
    }
    public byte[] generateRapportPdf() {
        long patients = patientRepository.count();
        long medecins = medecinRepository.count();
        long rdvs = rendezVousRepository.count();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("RAPPORT HEALTHCARE"));

        document.add(new Paragraph("Nombre Patients : " + patients));
        document.add(new Paragraph("Nombre Médecins : " + medecins));
        document.add(new Paragraph("Nombre Rendez-vous : " + rdvs));

        document.close();
        return out.toByteArray();
    }
}
