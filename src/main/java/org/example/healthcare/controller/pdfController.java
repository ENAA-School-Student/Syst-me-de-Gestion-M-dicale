package org.example.healthcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download")
@RequiredArgsConstructor
public class pdfController {
    private  final PdfService pdfService;

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','MEDECIN')")
    @GetMapping("/dossier/{id}")
    public ResponseEntity<byte[]> downloadDossier(@PathVariable Long id) {
        byte[] pdf = pdfService.generateDossierPdf(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dossier.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
