package org.example.healthcare.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.entity.DossierMedicalEntity;
import org.example.healthcare.repository.DossierMedicalRepository;
import org.example.healthcare.repository.PatientRepository;
import org.example.healthcare.repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {
    private  final DossierMedicalRepository dossierMedicalRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;

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
}
