package application.core.utils;

import application.core.model.Document;
import application.core.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentScheduler {
    private Long docFirstThreshold  = 30L;
    private Long docSecondThreshold = 60L;
    private String docFirstThresholdTemplateLocation  = "/resources/velocity/doc_30_days_template.vm";
    private String docSecondThresholdTemplateLocation = "/resources/velocity/doc_60_days_template.vm";

    @Autowired
    private DocumentRepository docs;

    @Autowired
    private DocumentMailer mailer;

    @Scheduled(fixedRate = 86400000L)
    public void checkDocumentStatus() {
        List<Document> documents = docs.findAll();

        documents.forEach(document -> {
            long crtDate = (new Date()).getTime();
            long docDate = document.getDateAdded().getTime();
            if (crtDate - docDate == docFirstThreshold) {
                mailer.SetVelocityTemplateLocation(docFirstThresholdTemplateLocation);
                mailer.SendMail(document);
            }
            else if (crtDate - docDate == docSecondThreshold) {
                mailer.SetVelocityTemplateLocation(docSecondThresholdTemplateLocation);
                mailer.SendMail(document);
            }
        });

        List<Document> toBeDeleted = documents.stream()
                .filter(document -> (new Date().getTime()) - document.getDateAdded().getTime() >= 60)
                .collect(Collectors.toList());

        toBeDeleted.forEach(document -> docs.delete(document.getId()));
    }
}
