package application.core.service;

import application.core.model.dr.DRDocument;
import com.aspose.words.Document;

import java.util.List;

public interface DRDocumentService {
    void saveDocumentOnServer(Document document, String filename);
    Integer saveDocumentInDBFirstPart(Document document, String filename);
    void saveDocumentInDBSecondPart(Document document, Integer documentId);
    List<DRDocument> getDocuments(String username);
}
