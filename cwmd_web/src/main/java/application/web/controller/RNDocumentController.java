package application.web.controller;

import application.core.model.rn.RNDocument;
import application.core.service.RNDocumentService;
import application.core.utils.UserUtil;
import application.web.converter.RNDocumentConverter;
import application.web.dto.RNDocumentDto;
import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/rn")
@RestController
public class RNDocumentController {

    @Autowired
    private RNDocumentService rnDocumentService;

    @Autowired
    private RNDocumentConverter rnDocumentConverter;

    @RequestMapping(method = RequestMethod.GET)
    public List<RNDocumentDto> getDocuments(HttpServletRequest request) {
        List<RNDocument> documents = new ArrayList<>();

        documents = rnDocumentService.getDocumentsByUsername(UserUtil.getCurrentUsername(request));

        return rnDocumentConverter.toDTOs(documents);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ResponseEntity<Integer> countDocuments() {
        int size = rnDocumentService.getAllDocuments().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/inFlow",method = RequestMethod.GET)
    public ResponseEntity<Integer> countPartOfAFlowDocuments() {
        int size = rnDocumentService.getAllPartOfAFlowDocuments().size();
        if (size == 0) {
            return new ResponseEntity<>(size-1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Integer> uploadRN(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        Workbook workbook = null;
        try {
            workbook = new Workbook(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDate date = LocalDate.now();

        if (workbook != null) {
            Cell cell = workbook.getWorksheets().get(0).getCells().get("B8");

            System.out.println("cell.getValue().toString() = " + cell.getValue().toString());

            Integer documentId = rnDocumentService.saveDocumentInDB(workbook, date, request);
            rnDocumentService.saveDocumentOnDisk(workbook, date, documentId, request);
            return new ResponseEntity<>(documentId, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/RN_document_sample", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<InputStreamResource> downloadRNSample() throws IOException {
        ClassPathResource file = new ClassPathResource("sample-files/RN_info_sample.xlsx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam Integer documentId, HttpServletRequest request) throws IOException {
        RNDocument document = rnDocumentService.getDocument(documentId);
        Date dateAdded = document.getDateAdded();
        LocalDate date = dateAdded.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String dateString = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
        String username = UserUtil.getCurrentUsername(request);
        ClassPathResource file = new ClassPathResource("files/" + username + "/rn/" + documentId + "/RN - " + dateString + ".xlsx");

        return ResponseEntity
                .ok()
                .contentLength(file.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(file.getInputStream()));

    }
}
