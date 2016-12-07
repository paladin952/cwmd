package application.web.controller;

import application.core.model.User;
import application.core.model.dr.DRDocument;
import application.core.service.DRDocumentService;
import application.core.utils.UserUtil;
import application.web.misc.ViewPath;
import com.aspose.words.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/document")
@RestController
// TODO: 03.12.2016 check how this works for angular and do necessary changes
public class DRDocumentController {

    private static Integer documentId = null;

    @Autowired
    private DRDocumentService drDocumentService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadDR() {
        return ViewPath.UPLOAD_DOCUMENT;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Integer uploadDR(@RequestParam("file") MultipartFile file) throws Exception {
//    public Integer uploadDR(@RequestParam("file") MultipartFile file, @RequestParam(value = "documentId", required=false) Integer documentId) throws Exception {
        Document document = null;
        Integer docId = null;
        try {
            document = new Document(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        drDocumentService.saveDocumentOnServer(document, file.getOriginalFilename());
        if (documentId == null) {
            documentId = drDocumentService.saveDocumentInDBFirstPart(document, file.getOriginalFilename());
            docId = documentId;
        } else {
            drDocumentService.saveDocumentInDBSecondPart(document, documentId);
            documentId = null;
        }

        return docId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DRDocument> getDocuments() {
        User currentUser = UserUtil.getCurrentUser();

        List<DRDocument> documents = new ArrayList<>();

        if (currentUser != null) {
            documents = drDocumentService.getDocuments(currentUser.getUsername());
        }

        return documents;
    }

    @RequestMapping(value = "/downloadFirst", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> downloadDRFirstPart()
            throws IOException {

        ClassPathResource file = new ClassPathResource("sample-files/DR_info_sample_First_part.docx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping(value = "/downloadSecond", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> downloadDRSecondPart()
            throws IOException {

        ClassPathResource file = new ClassPathResource("sample-files/DR_info_sample_Second_part.docx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
