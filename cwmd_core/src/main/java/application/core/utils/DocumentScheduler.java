package application.core.utils;

import application.core.model.Document;
import application.core.model.Flow;
import application.core.repository.DocumentRepository;
import application.core.repository.FlowRepository;
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
    private DocumentRepository documentRepository;
    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private DocumentMailer mailer;

    @Scheduled(fixedRate = 86400000L)
    public void checkDocumentStatus() {
        final Integer dayDivider = 1000 * 60 * 60 * 24;
        List<Document> documents = documentRepository.findAll();
        List<Flow> flows = flowRepository.findAll();

        documents.forEach(document -> {
            if (flows.stream().noneMatch(flow -> flow.getFlowDocuments()
                    .stream()
                    .anyMatch(flowDocument -> flowDocument.getDocument().equals(document)))) { // if document not part of any flows
                long crtDate = (new Date()).getTime();
                long docDate = document.getDateAdded().getTime();
                if ((crtDate - docDate) / dayDivider == docFirstThreshold) {
                    mailer.setVelocityTemplateLocation(docFirstThresholdTemplateLocation);
                    mailer.sendMail(document);
                } else if (crtDate - docDate == docSecondThreshold) {
                    mailer.setVelocityTemplateLocation(docSecondThresholdTemplateLocation);
                    mailer.sendMail(document);
                }
            }
        });

        List<Document> toBeDeleted = documents.stream()
                .filter(document -> ((((new Date().getTime() - document.getDateAdded().getTime()) / dayDivider) >= docSecondThreshold)))
                .collect(Collectors.toList());

        toBeDeleted.forEach(document -> documentRepository.delete(document.getId()));
    }
}
