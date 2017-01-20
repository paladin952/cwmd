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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/dr")
@RestController
// TODO: 03.12.2016 check how this works for angular and do necessary changes
public class DRDocumentController {

    private static Integer documentId = null;

    @Autowired
    private DRDocumentService drDocumentService;

    @Autowired
    private DRDocumentConverter drDocumentConverter;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadDR() {
        return ViewPath.UPLOAD_DOCUMENT;
    }

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

    @RequestMapping(method = RequestMethod.GET)
    public List<DRDocumentDto> getDocuments(HttpServletRequest request) {
        List<DRDocument> documents;

        documents = drDocumentService.getDocumentsByUsername(UserUtil.getCurrentUsername(request));

        return drDocumentConverter.toDTOs(documents);
    }

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

    @RequestMapping(value = "/inFlow",method = RequestMethod.GET)
    public ResponseEntity<Integer> countPartOfAFlowDocuments() {
        int size = drDocumentService.getAllPartOfAFlowDocuments().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
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

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id, @RequestParam String part) throws IOException {
        DRDocument drDocument = drDocumentService.getDocument(id);
        String path = drDocument.getPath();
        String newPath = path.replace("DR", "DR " + part + " -");
        InputStream in = new FileInputStream(newPath);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set("Content-Disposition", "inline; filename=" + drDocument.getName() + " " + part + ".docx");
        header.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(new InputStreamResource(in), header, HttpStatus.OK);
    }
}
