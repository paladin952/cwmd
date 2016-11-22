package application.core.service;

import com.aspose.words.Document;

public interface DRDocumentService {
    void saveDocumentOnServer(Document document, String filename);
    Integer saveDocumentInDBFirstPart(Document document, String filename);
    void saveDocumentInDBSecondPart(Document document, Integer documentId);
}
