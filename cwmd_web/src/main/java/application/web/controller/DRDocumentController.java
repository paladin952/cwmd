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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/dr")
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
    public Integer uploadDR(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        Document document = null;
        Integer docId = null;
        document = new Document(file.getInputStream());

        drDocumentService.saveDocumentOnServer(document);
        if (documentId == null) {
            documentId = drDocumentService.saveDocumentInDBFirstPart(document, request);
            docId = documentId;
        } else {
            drDocumentService.saveDocumentInDBSecondPart(document, documentId);
            documentId = null;
        }

        return docId;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DRDocumentDto> getDocuments(HttpServletRequest request) {
        List<DRDocument> documents = new ArrayList<>();

        documents = drDocumentService.getDocuments(UserUtil.getCurrentUsername(request));

        return new DRDocumentConverter().toDTOs(documents);
    }

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
}
