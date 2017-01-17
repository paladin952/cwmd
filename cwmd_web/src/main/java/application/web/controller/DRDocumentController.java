package application.web.controller;

import application.core.model.dr.DRDocument;
import application.core.service.DRDocumentService;
import application.core.utils.UserUtil;
import application.web.converter.DRDocumentConverter;
import application.web.dto.DRDocumentDto;
import application.web.misc.ViewPath;
import com.aspose.words.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * REST Controller for {@link DRDocument}.
 */
@RequestMapping("/dr")
@RestController
// TODO: 03.12.2016 check how this works for angular and do necessary changes
public class DRDocumentController {

    private static Integer documentId = null;

    @Autowired
    private DRDocumentService drDocumentService;

    @Autowired
    private DRDocumentConverter drDocumentConverter;

    /**
     * Returns the path for the document upload.
     * @return Returns a string representing the document upload path.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadDR() {
        return ViewPath.UPLOAD_DOCUMENT;
    }

    /**
     * Saves a document on the server.
     * @param file The file to be uploaded.
     * @return the ID of the uploaded document.
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Integer> uploadDR(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {
        Document document = null;
        Integer docId = null;
        try {
            document = new Document(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();

        if (document != null) {
            if (documentId == null) {
                documentId = drDocumentService.saveDocumentInDBFirstPart(document, date, request);
                drDocumentService.saveDocumentOnServer(document, date, documentId, "first", request);
                docId = documentId;
            } else {
                drDocumentService.saveDocumentInDBSecondPart(document, documentId);
                drDocumentService.saveDocumentOnServer(document, date, documentId, "second", request);
                documentId = null;
            }
            return new ResponseEntity<>(docId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns the documents for the current user.
     * @return A list of {@link DRDocumentDto}
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<DRDocumentDto> getDocuments(HttpServletRequest request) {
        List<DRDocument> documents;

        documents = drDocumentService.getDocumentsByUsername(UserUtil.getCurrentUsername(request));

        return drDocumentConverter.toDTOs(documents);
    }

    /**
     * Returns the number of documents on the server.
     * @return Integer value
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ResponseEntity<Integer> countDocuments() {
        List<DRDocument> allDocuments = drDocumentService.getAllDocuments();
        int size = allDocuments.size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    /**
     * Returns the number of documents in the flow
     * @return Integer value
     */
    @RequestMapping(value = "/inFlow",method = RequestMethod.GET)
    public ResponseEntity<Integer> countPartOfAFlowDocuments() {
        int size = drDocumentService.getAllPartOfAFlowDocuments().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    /**
     * Downloads the first document template
     * @return InputStreamResource corresponding to the first document template
     */
    @RequestMapping(value = "/First_part_sample", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> downloadDRFirstPart()
            throws IOException {

        ClassPathResource file = new ClassPathResource("sample-files/DR_info_sample_First_part.docx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    /**
     * Downloads the second document template
     * @return InputStreamResource corresponding to the second document template
     */
    @RequestMapping(value = "/Second_part_sample", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> downloadDRSecondPart()
            throws IOException {

        ClassPathResource file = new ClassPathResource("sample-files/DR_info_sample_Second_part.docx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    /**
     * Downloads a document based on the given ID and the given part
     * @param documentId the ID of the document to be downloaded
     * @param part the part of the document to be downloaded
     * @return InputStreamResource corresponding to the document
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Integer documentId, @RequestParam String part, HttpServletRequest request) throws IOException {

        DRDocument drDocument = drDocumentService.getDocument(documentId);
        Date dateAdded = drDocument.getDateAdded();
        LocalDate date = dateAdded.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
        String username = UserUtil.getCurrentUsername(request);
        ClassPathResource file = new ClassPathResource("files/" + username + "/dr" + documentId + "/DR " + part + " - " + dateString + ".docx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
